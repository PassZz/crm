package crm.filter;

import crm.commons.constants.Constants;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession(false);

        if ("/settings/qx/user/toLogin.do".equals(servletRequest.getServletPath()) ||
                (session != null && session.getAttribute(Constants.SESSION_USER) != null)){
            chain.doFilter(request, response);
        }else {
            servletResponse.sendRedirect("redirect:/");
        }

    }

    @Override
    public void destroy() {

    }
}
