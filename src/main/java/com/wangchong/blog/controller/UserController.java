package com.wangchong.blog.controller;

import com.wangchong.blog.annotation.LoginCheck;
import com.wangchong.blog.entity.Pv;
import com.wangchong.blog.entity.User;
import com.wangchong.blog.service.ArticleService;
import com.wangchong.blog.service.PvService;
import com.wangchong.blog.service.UserService;
import com.wangchong.blog.websocket.NettyConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PvService pvService;
    @Autowired
    private ArticleService articleService;

    /**
     * 后台登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login.do")
    public ModelAndView login(@RequestParam(value = "username",required = true) String username,
                              @RequestParam(value = "password",required = true) String password,
                              HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        User user = userService.login(username,password);
        if(user != null){
            request.getSession().setAttribute("user",user);
            mav.setViewName("redirect:/user/main.do");
        }else{
            mav.setViewName("login");
        }
        return mav;
    }

    /**
     * 首页加载数据
     * @param request
     * @return
     */
    @LoginCheck
    @RequestMapping("/main.do")
    public ModelAndView main( HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        Pv pv = pvService.getByDate(new Date());
        int articleNum = articleService.getArticleCount();
        mav.addObject("user",user);
        mav.addObject("pv",pv);
        mav.addObject("articleNum",articleNum);
        mav.setViewName("/admin/index");
        return mav;
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping("/logout.do")
    public ModelAndView logout(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        try {
            request.getSession().invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName("redirect:/toLogin.do");
        return mav;
    }

    @RequestMapping("/websocket")
     public ModelAndView websocket(){
         ModelAndView mav = new ModelAndView();
         mav.setViewName("/page/websocket");
         return mav;
     }

     @ResponseBody
     @RequestMapping("/webLogin")
     public Map<String,Object> webLogin(String name){
         Map<String,Object> map = new HashMap<>();
         if(StringUtils.isNotBlank(name)){
             NettyConfig.userList.add(name);
         }
         map.put("result",true);
         map.put("name",name);
         map.put("user",NettyConfig.userList);
         return map;
     }

    @ResponseBody
    @RequestMapping("/webLogout")
    public Map<String,Object> webLogout(String name){
        Map<String,Object> map = new HashMap<>();
        if(NettyConfig.userList.contains(name)){
            NettyConfig.userList.remove(name);
        }
        map.put("result",true);
        return map;
    }

}