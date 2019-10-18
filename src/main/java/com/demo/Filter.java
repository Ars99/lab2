package com.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filter")
public class Filter implements javax.servlet.Filter {
    private Singleton session;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Cookie[] cookies = request.getCookies();

        String cookieName = "sessionId";
        if(!(cookies == null)) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName()) && session.contain(cookie.getValue())) {
                    request.getRequestDispatcher("/hello_inside.jsp").forward(request,response);

                }
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
       session = Singleton.getInstance();
    }

}
