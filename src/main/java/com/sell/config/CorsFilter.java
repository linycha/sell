package com.sell.config;

/**
 * 跨域过滤器
 * @author linyuc
 * @date 2020/3/27 21:46
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CorsFilter ")
@Configuration
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        /*HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        chain.doFilter(req, res);*/
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String method = request.getMethod();
        // this origin value could just as easily have come from a database
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Accept, Origin, X-Requested-With, Content-Type,Last-Modified,device,token");
        if ("OPTIONS".equals(method)) {//检测是options方法则直接返回200
            response.setStatus(HttpStatus.OK.value());
        } else {
            chain.doFilter(req, res);
        }
    }
}
