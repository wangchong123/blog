package com.wangchong.blog.controller;

import com.wangchong.blog.entity.Article;
import com.wangchong.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
}
