package com.restaurant.config;

import com.restaurant.entity.result.Response;
import com.restaurant.entity.result.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.alibaba.fastjson.JSON;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebFilter(
        filterName = "customFilter",
        urlPatterns = {"/*"})
public class RestaurantFilter implements Filter {
    @Autowired
    private StringRedisTemplate redis;
    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        String url="";
                url=request.getRequestURL().toString();
//        log.error("请求---->>>>>>>>>"+url);
        if(url.contains("login")||url.contains("swagger")||url.contains("ui")||url.contains("v2")){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            String token =request.getParameter("token");
            String account=request.getParameter("accountCode");
            if (token!=null){
                String reAccount=redis.opsForValue().get(token);
                if(reAccount!=null&&account.equals(reAccount)){
                    redis.opsForValue().set(token,account,300);
                    filterChain.doFilter(servletRequest, servletResponse);
                }else {
                    HttpServletResponse response=(HttpServletResponse)servletResponse;
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter out = null ;
                    try{
//                        log.error("token失效");
                        Response response1=Response.builder().msg("token失效").code(ResponseStatus.INVALID_TOKEN).build();
                        String res=JSON.toJSONString(response1);

                        out = servletResponse.getWriter();
                        out.append(res);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        response.setStatus(500);
                    }
                }
            }else {
                log.error("token为空");
            }
        }

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