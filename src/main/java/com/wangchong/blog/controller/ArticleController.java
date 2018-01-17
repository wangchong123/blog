package com.wangchong.blog.controller;

import com.wangchong.blog.entity.Article;
import com.wangchong.blog.service.ArticleService;
import com.wangchong.blog.util.CommonUtil;
import com.wangchong.blog.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request,
            @RequestParam(value = "type",required = false) Long type){
        ModelAndView mav = new ModelAndView();
        List<Article> articleList = articleService.queryArticleList(type);
        mav.addObject("articleList",articleList);
        mav.setViewName("/index");
        return mav;
    }

    @RequestMapping("/articleDetail.do")
    public ModelAndView articleDetail(HttpServletRequest request,
                              @RequestParam(value = "id",required = true) Long id){
        ModelAndView mav = new ModelAndView();
        Article article = articleService.getArticle(id);
        mav.addObject("article",article);
        mav.setViewName("/articleDetail");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/addOpt3.do")
    public Map<String,Object> opt3(HttpServletRequest request,
                    @RequestParam(value = "id",required = false) Long id){
        Map<String,Object> map = new HashMap<>();
        String ip = CommonUtil.getIpAddr(request);
        articleService.updateOpt(id, ConstantUtil.OPT3);
        System.out.println(ip);
        map.put("ip",ip);
        return map;

    }
}
