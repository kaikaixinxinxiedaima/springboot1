package com.test.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------------------MyFilter init----------------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("----------------------MyFilter start----------------------");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("----------------------MyFilter finish----------------------");
    }

    @Override
    public void destroy() {
        System.out.println("----------------------MyFilter destroy----------------------");
    }
}
