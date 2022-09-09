package ru.job4j.cinema.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        if (uri.endsWith("getTicket") && req.getSession().getAttribute("user") == null) {
            req.getSession().setAttribute("mustLoginForTakeTicket", true);
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        if (uri.endsWith("getTicket") && req.getParameter("place") == null) {
            req.getSession().setAttribute("not_specify_place", true);
        }
        filterChain.doFilter(req, res);
    }
}
