package com.team.housebackapi.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "CORSFilter", urlPatterns ="/*")
public class CORSFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", "null"); //支持跨域
        response.setHeader("Access-Control-Allow-Credentials", "true");  //支持session异步请求，共享session
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Token");
        chain.doFilter(req, resp);  //放行
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
/**
 *  withCredentials：表示XMLHttpRequest是否接收cookies和发送cookies，
 * 也就是说如果该值是false，响应头的Set-Cookie，浏览器也不会理，
 * 并且即使有目标站点的cookies，浏览器也不会发送。
 *
 //指定允许其他域名访问
 'Access-Control-Allow-Origin:http://172.20.0.206'//一般用法（*，指定域，动态设置），3是因为*不允许携带认证头和cookies
 //是否允许后续请求携带认证信息（cookies）,该值只能是true,否则不返回
 response.setHeader("Access-Control-Allow-Credentials", "true");
 Access-Control-Allow-Origin设置为空*/
