package internet.shop.controller.order;

import internet.shop.lib.Injector;
import internet.shop.service.OrderService;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("internet.shop");
    private OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long orderId = Long.valueOf(req.getParameter("id"));
        orderService.delete(orderId);
        resp.sendRedirect(req.getContextPath() + "/order/all");
    }
}
