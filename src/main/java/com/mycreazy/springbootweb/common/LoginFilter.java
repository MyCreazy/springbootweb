package com.mycreazy.springbootweb.common;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Time: 上午10:16
 **/
@Order(1)
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter  implements Filter {
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/login", "")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest)servletRequest);
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

        int index = path.indexOf("/", 0);
        int index2 = path.indexOf("/", 1);
        String regex = path.substring((index == -1?0:index), (index2 == -1?0:index2));

        boolean allowedPath = ALLOWED_PATHS.contains(regex);

        HttpSession session = request.getSession();
        if (!allowedPath && !"1".equals(session.getAttribute("__status")))
            response.sendRedirect("/login/login.html");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
