package internet.shop.security.impl;

import internet.shop.exceptions.AuthenticationException;
import internet.shop.lib.Inject;
import internet.shop.lib.Service;
import internet.shop.model.User;
import internet.shop.security.AuthenticationService;
import internet.shop.service.UserService;
import internet.shop.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> userFromDB = userService.findByLogin(login);
        if (userFromDB.isPresent() && userFromDB.get().getPassword()
                .equals(HashUtil.hashPassword(password, userFromDB.get().getSalt()))) {
            return userFromDB.get();
        }
        throw new AuthenticationException("Incorrect username or password!");
    }
}
