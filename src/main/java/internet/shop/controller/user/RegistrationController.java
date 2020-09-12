package internet.shop.controller.user;

import internet.shop.lib.Injector;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import internet.shop.service.ShoppingCartService;
import internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("internet.shop");
    private static final Logger logger = Logger.getRootLogger();
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String passwordConfirm = req.getParameter("pwd-confirm");
        if (password.equals(passwordConfirm)) {
            User newUser = new User(name, login, password);
            userService.create(newUser);
            ShoppingCart newShoppingCart = new ShoppingCart(newUser.getId());
            shoppingCartService.create(newShoppingCart);
            logger.info("New user added!");
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            logger.warn("Password and confirm password aren't equal.");
            req.setAttribute("message", "Password and Confirm password aren't the same!");
            req.setAttribute("name", name);
            req.setAttribute("login", login);
            req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp").forward(req, resp);
        }
    }
}
