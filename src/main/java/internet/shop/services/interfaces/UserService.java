package internet.shop.services.interfaces;

import internet.shop.models.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);

    Optional<User> getById(Long userId);

    User update(User user);

    boolean deleteById(Long userId);

    boolean delete(User user);

    List<User> getAllUsers();
}
