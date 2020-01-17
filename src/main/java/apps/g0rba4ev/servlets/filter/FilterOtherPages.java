package apps.g0rba4ev.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter", urlPatterns = "/doCalc")
public class FilterOtherPages implements javax.servlet.Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if ( session != null &&
                session.getAttribute("isAuthorized") != null &&
                (boolean)session.getAttribute("isAuthorized")) {
            chain.doFilter(req, res);
        }
        else{
            //move to login page
            res.sendRedirect(req.getContextPath());
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
