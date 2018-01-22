package com.wangchong.blog.controller;

import com.wangchong.blog.annotation.LoginCheck;
import com.wangchong.blog.entity.User;
import com.wangchong.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 后台登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login.do")
    public ModelAndView Login(@RequestParam(value = "username",required = true) String username,
                              @RequestParam(value = "password",required = true) String password,
                              HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        User user = userService.login(username,password);
        if(user != null){
            request.getSession().setAttribute("user",user);
            mav.addObject("user",user);
            mav.setViewName("admin/main");
        }else{
            mav.setViewName("login");
        }
        return mav;
    }
}
