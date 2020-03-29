package com.yrq.blog.intercepter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 09:45
 **/
public class LoginIntercepter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("user")==null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }


}
