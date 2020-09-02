package internet.shop.dao.impl;

import internet.shop.dao.interfaces.UserDao;
import internet.shop.db.Storage;
import internet.shop.lib.Dao;
import internet.shop.models.User;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> getById(Long userId) {
        return Storage.users.stream()
                .filter(x -> x.getId().equals(userId))
                .findFirst();
    }

    @Override
    public User update(User user) {
        return Storage.users.stream()
                .filter(x -> x.getId().equals(user.getId()))
                .map(x -> x = user)
                .findAny()
                .orElseThrow();
    }

    @Override
    public boolean deleteById(Long userId) {
        return Storage.users.removeIf(x -> x.getId().equals(userId));
    }

    @Override
    public boolean delete(User user) {
        return deleteById(user.getId());
    }

    @Override
    public List<User> getAllUsers() {
        return Storage.users;
    }
}
