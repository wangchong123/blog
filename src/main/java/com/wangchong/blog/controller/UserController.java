package com.wangchong.blog.controller;

import com.wangchong.blog.entity.User;
import com.wangchong.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public ModelAndView Login(@RequestParam(value = "username",required = true) String username,
                              @RequestParam(value = "password",required = true) String password){
        ModelAndView mav = new ModelAndView();
        User user = userService.login(username,password);
        if(user != null){
            mav.addObject("user",user);
            mav.setViewName("index");
        }else{
            mav.setViewName("login");
        }
        return mav;
    }
}
