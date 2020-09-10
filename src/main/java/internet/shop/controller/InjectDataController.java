package internet.shop.controller;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.model.ShoppingCart;
import internet.shop.model.User;
import internet.shop.service.ProductService;
import internet.shop.service.ShoppingCartService;
import internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User mike = new User("Mike", "ekim", "1234");
        User nolan = new User("Nolan", "tenet", "nolan");
        userService.create(mike);
        userService.create(nolan);

        Product apple = new Product("Apple", 1500);
        Product xiaomi = new Product("Xiaomi", 5000);
        productService.create(apple);
        productService.create(xiaomi);

        shoppingCartService.create(new ShoppingCart(mike.getId()));
        shoppingCartService.create(new ShoppingCart(nolan.getId()));
        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
