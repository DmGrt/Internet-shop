package internet.shop.controller.user;

import internet.shop.exceptions.AuthenticationException;
import internet.shop.lib.Injector;
import internet.shop.model.Role;
import internet.shop.model.User;
import internet.shop.security.AuthenticationService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("internet.shop");
    private static final String USER_ID = "user_id";
    private AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pwd = req.getParameter("pwd");
        User user;
        try {
            user = authenticationService.login(login, pwd);
            HttpSession session = req.getSession();
            session.setAttribute(USER_ID, user.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
            return;
        }
        if (user.getRoles().contains(Role.of("USER"))) {
            resp.sendRedirect(req.getContextPath() + "/");
        }
        if (user.getRoles().contains(Role.of("ADMIN"))) {
            resp.sendRedirect(req.getContextPath() + "/admin");
        }
    }
}
