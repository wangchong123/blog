package com.wangchong.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangchong.blog.annotation.LoginCheck;
import com.wangchong.blog.entity.Article;
import com.wangchong.blog.service.ArticleService;
import com.wangchong.blog.util.CommonUtil;
import com.wangchong.blog.util.ConstantUtil;
import com.wangchong.blog.util.LuceneUtil;
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

    /**
     * 主页
     * @param request
     * @param type
     * @param currPage
     * @param pageSize
     * @return
     */
   // @LoginCheck
    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request,
                              @RequestParam(value = "type", required = false) Long type,
                              @RequestParam(value = "currPage", required = false,defaultValue = "1") Integer currPage,
                              @RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize) {
        ModelAndView mav = new ModelAndView();
        List<Article> articleList = articleService.queryArticleList(type,currPage,pageSize);
        PageInfo<Article> page = new PageInfo<Article>(articleList);
        mav.addObject("articleList", articleList);
        mav.addObject("page",page);
        mav.setViewName("/index");
        return mav;
    }

    /**
     * 文章详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/articleDetail.do")
    public ModelAndView articleDetail(HttpServletRequest request,
                              @RequestParam(value = "id",required = true) Long id){
        ModelAndView mav = new ModelAndView();
        Article article = articleService.getArticle(id);
        mav.addObject("article",article);
        mav.setViewName("/article");
        return mav;
    }

    /**
     * 浏览量记录
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/addOpt3.do")
    public Map<String,Object> opt3(HttpServletRequest request,
                    @RequestParam(value = "id",required = false) Long id){
        Map<String,Object> map = new HashMap<>();
        String ip = CommonUtil.getIpAddr(request);
        articleService.updateOpt(id, ConstantUtil.OPT3);
        map.put("ip",ip);
        return map;
    }

    @ResponseBody
    @RequestMapping("/createArticle.do")
    public Map<String,Object> createArticle(HttpServletRequest request,String title,String describe,String content,
                                            Integer status,Integer type,Integer scope,String photo){
        Map<String,Object> map = new HashMap<>();
        boolean result = articleService.createArticle(title,describe,content,type,scope,status,photo);
        if(result){
            LuceneUtil.createIndex(new Article());
        }
        map.put("result",result);
        return map;
    }

    /**
     * 生成索引
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/createLuceneIndex.do")
    public Map<String,Object> createLuceneIndex(HttpServletRequest request){
        boolean result = false;
        List<Article> list = articleService.queryArticleList();
        LuceneUtil.createIndex(list);
        result = true;
        Map<String,Object> map = new HashMap<>();
        map.put("result",result);
        return map;
    }





}
