package internet.shop.controller.shoppingcart;

import internet.shop.lib.Injector;
import internet.shop.service.ProductService;
import internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductFromCartController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("internet.shop");
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        shoppingCartService.deleteProduct(shoppingCartService.getByUserId(USER_ID),
                productService.get(productId));
        resp.sendRedirect(req.getContextPath() + "/shopping-cart/products");
    }
}
