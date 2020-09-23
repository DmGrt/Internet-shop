package internet.shop.dao.jdbc;

import internet.shop.dao.UserDao;
import internet.shop.exceptions.DataProcessingException;
import internet.shop.lib.Dao;
import internet.shop.model.Role;
import internet.shop.model.User;
import internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        User user = null;
        String query = "SELECT * FROM users WHERE login = ? AND is_deleted = FALSE";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get user by login = "
                    + login, e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.execute();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                user.setId(result.getLong(1));
            }
            setUserRoles(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to create user "
                    + user.getName(), e);
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        User user = null;
        String query = "SELECT * FROM users WHERE user_id = ? AND is_deleted = FALSE";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get user by id = "
                    + id, e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get all users!", e);
        }
        return users;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, login = ?, password = ? "
                + "WHERE is_deleted = FALSE AND user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to update product "
                    + user.getId() + ", " + user.getName(), e);
        }
        deleteOldRoles(user);
        setUserRoles(user);
        return user;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE users SET is_deleted = TRUE WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to delete user with id = "
                    + id, e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        User user = new User(name, login, password);
        user.setId(id);
        user.setRoles(getRolesFromDB(id));
        return user;
    }

    private Set<Role> getRolesFromDB(Long userId) {
        Set<Role> roles = new HashSet<>();
        String query = "SELECT role_name FROM roles JOIN users_roles ur "
                + "ON roles.role_id = ur.role_id WHERE ur.user_id = ?";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = Role.of(resultSet.getString("role_name").toUpperCase());
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get roles by id = "
                    + userId, e);
        }
        return roles;
    }

    private void setUserRoles(User user) {
        String query = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        try (Connection conn = ConnectionUtil.getConnection()) {
            for (Role role : user.getRoles()) {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setLong(1, user.getId());
                statement.setLong(2, getRoleId(role));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to set user roles "
                    + user.getId(), e);
        }
    }

    private Long getRoleId(Role role) {
        Long id = null;
        String query = "SELECT role_id FROM roles WHERE role_name = ?";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(role.getRoleName()));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong("role_id");
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get role id "
                    + role, e);
        }
        return id;
    }

    private void deleteOldRoles(User user) {
        String query = "DELETE FROM users_roles WHERE user_id = ?";
        try (Connection conn = ConnectionUtil.getConnection()) {
            for (Role role : user.getRoles()) {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setLong(1, user.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to set user roles "
                    + user.getId(), e);
        }
    }
}
