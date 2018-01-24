package com.wangchong.blog.config;

import com.wangchong.blog.annotation.LoginCheck;
import com.wangchong.blog.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //不是方法直接跳过
        if(!(o instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handler = (HandlerMethod) o;
        Method method = handler.getMethod();
        //方法上是否有LoginCheck注解
        LoginCheck loginCheck = method.getAnnotation(LoginCheck.class);
        if(loginCheck != null){
            User user=(User) request.getSession().getAttribute("user");
            if(user != null){
                return true;
            }
        }else{
            return true;
        }
       response.sendRedirect("/toLogin.do");
       return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
