package internet.shop.controller.product;

import internet.shop.lib.Injector;
import internet.shop.service.ProductService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductsForManageController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("internet.shop");
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/product/delete.jsp")
                .forward(req, resp);
    }
}
