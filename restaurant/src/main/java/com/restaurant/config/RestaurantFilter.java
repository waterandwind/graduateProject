//package com.restaurant.config;
//
//import com.restaurant.entity.result.Response;
//import com.restaurant.entity.result.ResponseStatus;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import com.alibaba.fastjson.JSON;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.concurrent.TimeUnit;
//
//@Slf4j
//@WebFilter(
//        filterName = "customFilter",
//        urlPatterns = {"/*"})
//public class RestaurantFilter implements Filter {
//    @Autowired
//    private StringRedisTemplate redis;
//
//    @Override
//    public void doFilter(
//            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//            throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String url = "";
//        //跨域
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        //允许请求携带认证信息(cookie)
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        //指定允许其他域名访问
//        response.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
//        //允许请求的类型
//        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
//        //允许的请求头字段
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token,uid");
//        //设置预检请求的有效期
//        //浏览器同源策略：出于安全考虑，浏览器限制跨域的http请求。怎样限制呢？通过发送两次请求：预检请求、用户请求。
//        //1、预检请求作用：获知服务器是否允许该跨域请求：如果允许，才发起第二次真实的请求；如果不允许，则拦截第二次请求
//        //2、单位:s,在此期间不用发送预检请求。
//        //3、若为0：表示每次请求都发送预检请求,每个ajax请求之前都会先发送预检请求。
//        response.setHeader("Access-Control-Max-Age", "3600");
//        //OPTIONS Method表示浏览器发送的预检请求。
//        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//
//            /* 自己的代码 */
//            url = request.getRequestURL().toString();
//        log.error("请求---->>>>>>>>>"+url);
//            if (url.contains("login") || url.contains("swagger") || url.contains("ui") || url.contains("v2")|| url.contains("upload")|| url.contains("imgShow")) {
//                filterChain.doFilter(servletRequest, servletResponse);
//            } else {
//                String token = request.getHeader("token");
//                log.error("获得toekn:"+token);
//                String account = request.getHeader("uid");
//                if (token != null) {
//                    String reAccount = redis.opsForValue().get(token);
//                    if (reAccount != null && account.equals(reAccount)) {
//                        redis.opsForValue().set(token, account, 30000, TimeUnit.SECONDS);
//                        filterChain.doFilter(servletRequest, servletResponse);
//                    } else {
//                        response = (HttpServletResponse) servletResponse;
//                        response.setCharacterEncoding("UTF-8");
//                        response.setContentType("application/json; charset=utf-8");
//                        PrintWriter out = null;
//                        try {
////                        log.error("token失效");
//                            Response response1 = Response.builder().msg("token失效").code(ResponseStatus.INVALID_TOKEN).build();
//                            String re = JSON.toJSONString(response1);
//
//                            out = servletResponse.getWriter();
//                            out.append(re);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            response.setStatus(500);
//                        }
//                    }
//                } else {
//                    log.error("token为空");
//                }
//            }
//        }
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.error("customFilter --->filer init");
//    }
//
//
//    @Override
//    public void destroy() {
//        log.error("customFilter --->filter destroy");
//    }
//
//}