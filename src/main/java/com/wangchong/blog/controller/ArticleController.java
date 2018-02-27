package com.wangchong.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangchong.blog.annotation.LoginCheck;
import com.wangchong.blog.annotation.TraceLog;
import com.wangchong.blog.entity.Article;
import com.wangchong.blog.entity.Type;
import com.wangchong.blog.service.ArticleService;
import com.wangchong.blog.service.TypeService;
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
    @Autowired
    private TypeService typeService;

    /**
     * 主页
     * @param request
     * @param type
     * @param currPage
     * @param pageSize
     * @return
     */
    @TraceLog(name = "首页")
    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request,
                              @RequestParam(value = "type", required = false) Long type,
                              @RequestParam(value = "currPage", required = false,defaultValue = "1") Integer currPage,
                              @RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize) {
        ModelAndView mav = new ModelAndView();
        List<Article> articleList = articleService.queryArticleList(type,currPage,pageSize);
        PageInfo<Article> page = new PageInfo<Article>(articleList);
        if(type != null){
            Type o = typeService.getType(type);
            mav.addObject("type",o.getName());
        }
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
        articleService.updateOpt(id, ConstantUtil.OPT3);
        return map;
    }

    /**
     * 创建文章
     * @param request
     * @param title
     * @param describe
     * @param content
     * @param status
     * @param type
     * @param scope
     * @param photo
     * @return
     */
    @RequestMapping("/createArticle.do")
    public ModelAndView createArticle(HttpServletRequest request,String title,String describe,String content,
                                            Integer status,Long type,Integer scope,String photo){
        ModelAndView mav = new ModelAndView();
        Article article = articleService.insertArticle(title,describe,content,type,scope,status,photo);
        if(article != null && article.getStatus().intValue()==1){
            LuceneUtil.createIndex(article);
        }

        mav.setViewName("redirect:/admin/listArticle.do");
        return mav;
    }

    /**
     * 更新
     * @param request
     * @param title
     * @param describe
     * @param content
     * @param id
     * @param type
     * @param scope
     * @param photo
     * @return
     */
    @RequestMapping("/updateArticle.do")
    public ModelAndView updateArticle(HttpServletRequest request,String title,String describe,String content,
                                      Long id,Long type,Integer scope,String photo){
        ModelAndView mav = new ModelAndView();
        boolean result = articleService.updateArticle(id,title,describe,content,type,scope,photo);
       /* if(result){
            LuceneUtil.createIndex(new Article());
        }*/
        mav.setViewName("redirect:/admin/listArticle.do");
        return mav;
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


    /**
     * 搜索
     * @param request
     * @param source
     * @return
     */
    @RequestMapping("/search.do")
    public ModelAndView search(HttpServletRequest request,String source){
        ModelAndView mav = new ModelAndView();
        List<Article> list = LuceneUtil.search(source);
        mav.addObject("list",list);
        mav.addObject("source",source);
        mav.setViewName("/search");
        return mav;
    }

    /**
     * 根据浏览量查询
     * @return
     */
    @TraceLog(name = "浏览量查询")
    @ResponseBody
    @RequestMapping("/queryRankByOpt3.do")
    public Map<String,Object> queryRankByOpt3(){
        Map<String,Object> map = new HashMap<>();
        List<Article> list = articleService.queryRankByOpt3();
        map.put("list",list);
        return map;
    }

    /**
     * 后台查询全部 假分页
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxQueryList.do")
    public Map<String,Object> ajaxQueryList(){
        List<Article> list = articleService.queryArticleList();
        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        return map;
    }

    /**
     * ajax根据id查询文章
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxGetArticle.do")
    public Map<String,Object> ajaxGetArticle(Long id){
        Article article = articleService.getArticle(id);
        Map<String,Object> map = new HashMap<>();
        map.put("data",article);
        return map;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxDeleteArticle.do")
    public Map<String,Object> ajaxDeleteArticle(Long id){
        Map<String,Object> map = new HashMap<>();
        boolean result = articleService.deleteArticle(id);
        map.put("result",result);
        return map;
    }





}
