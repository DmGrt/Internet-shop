package internet.shop.controller;

import internet.shop.lib.Injector;
import internet.shop.model.Role;
import internet.shop.model.User;
import internet.shop.service.UserService;
import java.io.IOException;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector injector = Injector.getInstance("internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("time", LocalTime.now());
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        User user = userService.get(userId);
        if (user.getRoles().contains(Role.of("USER"))) {
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }
        if (user.getRoles().contains(Role.of("ADMIN"))) {
            req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
        }
    }
}
