package ru.job4j.servlets.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by User2 on 21.07.2018.
 */
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getRequestURI().contains("auth")) {
            filterChain.doFilter(req, servletResponse);
        } else {
            HttpSession session = req.getSession();
            synchronized (session) {
                if (session.getAttribute("login") == null) {
                    ((HttpServletResponse) servletResponse).sendRedirect(req.getContextPath() + "/auth");
                } else {
                    filterChain.doFilter(req, servletResponse);
                }
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
