package apps.g0rba4ev.servlets.filter;



import apps.g0rba4ev.DAO.DB;
import apps.g0rba4ev.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/")
public class AuthFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws ServletException, IOException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        req.setCharacterEncoding("UTF-8");

        //if user already authorized
        if ( session != null &&
                session.getAttribute("isAuthorized") != null &&
                (boolean)session.getAttribute("isAuthorized")) {

            req.getRequestDispatcher("/WEB-INF/view/calculator.jsp").forward(req, res);

        } else if(login != null && password != null){

            DB database = new DB();
            User user = database.getUserByLogin(login);

            if(user != null && user.getPassword().equals(password)){
                session.setAttribute("isAuthorized", true);
                req.getRequestDispatcher("/WEB-INF/view/calculator.jsp").forward(req, res);
            } else {
                if(user == null){
                    database.addUser(login, password);
                }
                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
            }


        } else {

                req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);

        }

    }

    public void init(FilterConfig config) throws ServletException {
    }

}
