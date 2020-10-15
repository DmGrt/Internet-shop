package internet.shop.controller.product;

import internet.shop.lib.Injector;
import internet.shop.model.Product;
import internet.shop.service.ProductService;
import internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductToCartController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector injector = Injector.getInstance("internet.shop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        Long productId = Long.valueOf(req.getParameter("id"));
        Product product = productService.get(productId);
        shoppingCartService.addProduct(shoppingCartService.getByUserId(userId), product);
        resp.sendRedirect(req.getContextPath() + "/product/all");
    }
}
