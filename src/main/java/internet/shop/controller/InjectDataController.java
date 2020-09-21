package internet.shop.controller;

import internet.shop.lib.Injector;
import internet.shop.model.Role;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import internet.shop.service.ShoppingCartService;
import internet.shop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User mike = new User("Mike");
        mike.setLogin("ekim");
        mike.setPassword("1234");
        mike.setRoles(Set.of(Role.of("USER")));
        userService.create(mike);

        User nolan = new User("Nolan");
        nolan.setLogin("tenet");
        nolan.setPassword("nolan");
        nolan.setRoles(Set.of(Role.of("USER")));
        userService.create(nolan);

        User admin = new User("admin");
        admin.setLogin("admin");
        admin.setPassword("admin");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);

        shoppingCartService.create(new ShoppingCart(mike.getId()));
        shoppingCartService.create(new ShoppingCart(nolan.getId()));
        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
