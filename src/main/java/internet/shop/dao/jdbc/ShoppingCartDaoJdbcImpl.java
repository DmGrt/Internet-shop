package internet.shop.dao.jdbc;

import internet.shop.dao.ShoppingCartDao;
import internet.shop.exceptions.DataProcessingException;
import internet.shop.lib.Dao;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        ShoppingCart cart = null;
        String query = "SELECT * FROM shopping_carts WHERE user_id = ? AND is_deleted = FALSE";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cart = getCartFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get cart by user id = "
                    + userId, e);
        }
        return Optional.ofNullable(cart);
    }

    @Override
    public double getTotalPrice(Long id) {
        double total = 0;
        String query = "SELECT SUM(price) as total FROM products join shopping_carts_products scp "
                + "on products.product_id = scp.product_id where cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                total = resultSet.getDouble("total");
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get all carts!", e);
        }
        return total;
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping_carts (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, shoppingCart.getUserId());
            statement.execute();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                shoppingCart.setId(result.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to create cart "
                    + shoppingCart.getId(), e);
        }
        setNewProducts(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        ShoppingCart cart = null;
        String query = "SELECT * FROM shopping_carts WHERE cart_id = ? AND is_deleted = FALSE";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cart = getCartFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get cart by id = "
                    + id, e);
        }
        return Optional.ofNullable(cart);
    }

    @Override
    public List<ShoppingCart> getAll() {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        String query = "SELECT * FROM shopping_carts WHERE is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ShoppingCart cart = getCartFromResultSet(resultSet);
                shoppingCarts.add(cart);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get all carts!", e);
        }
        return shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        String query = "UPDATE shopping_carts SET user_id = ? "
                + "WHERE is_deleted = FALSE AND cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCart.getUserId());
            statement.setLong(2, shoppingCart.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to update cart "
                    + shoppingCart.getId(), e);
        }
        deleteOldProducts(shoppingCart);
        setNewProducts(shoppingCart);
        return shoppingCart;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE shopping_carts SET is_deleted = TRUE WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to delete cart with id = "
                    + id, e);
        }
    }

    private void setNewProducts(ShoppingCart cart) {
        String query = "INSERT INTO shopping_carts_products (cart_id, product_id) VALUES (?, ?)";
        try (Connection conn = ConnectionUtil.getConnection()) {
            for (Product product : cart.getProducts()) {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setLong(1, cart.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to set cart products "
                    + cart.getId(), e);
        }
    }

    private void deleteOldProducts(ShoppingCart cart) {
        String query = "DELETE FROM shopping_carts_products WHERE cart_id = ?";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, cart.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to delete cart products "
                    + cart.getId(), e);
        }
    }

    private ShoppingCart getCartFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("cart_id");
        Long userId = resultSet.getLong("user_id");
        ShoppingCart shoppingCart = new ShoppingCart(userId);
        shoppingCart.setId(id);
        shoppingCart.getProducts().addAll(getCartProducts(id));
        return shoppingCart;
    }

    private List<Product> getCartProducts(Long id) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.product_id, p.name, p.price FROM products p "
                + "JOIN shopping_carts_products scp "
                + "ON p.product_id = scp.product_id where cart_id = ?;";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String productName = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Product product = new Product(productName, price);
                product.setId(productId);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get products by id = " + id, e);
        }
    }
}
