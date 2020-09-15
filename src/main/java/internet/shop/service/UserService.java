package internet.shop.service;

import internet.shop.model.User;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {
    Optional<User> findByLogin(String login);
}
