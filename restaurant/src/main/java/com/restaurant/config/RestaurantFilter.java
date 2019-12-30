package com.restaurant.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Slf4j
@WebFilter(
        filterName = "customFilter",
        urlPatterns = {"/*"})
public class RestaurantFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        log.error("customFilter --->dofilter"+request.getRequestURL());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.error("customFilter --->filer init");
    }


    @Override
    public void destroy() {
        log.error("customFilter --->filter destroy");
    }
}