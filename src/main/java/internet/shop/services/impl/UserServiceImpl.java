package internet.shop.services.impl;

import internet.shop.dao.interfaces.UserDao;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.models.User;
import internet.shop.services.interfaces.UserService;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public Optional<User> getById(Long userId) {
        return userDao.getById(userId);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteById(Long userId) {
        return userDao.deleteById(userId);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
