package com.wangchong.blog.service;

import com.github.pagehelper.PageHelper;
import com.wangchong.blog.dao.ArticleDao;
import com.wangchong.blog.entity.Article;
import com.wangchong.blog.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public List<Article> queryArticleList(Long type,Integer currPage,Integer Pagesize){
        Map<String,Object> map = new HashMap<>();
        if(type != null){
            map.put("type",type);
        }
        PageHelper.startPage(currPage,Pagesize);
        return articleDao.queryList(map);
    }

    public Article getArticle(Long id){
        if(id != null){
            return articleDao.getById(id);
        }
        return null;
    }

    public void updateOpt(Long id,Integer opt){
        if(id != null && opt != null){
            if(opt.equals(ConstantUtil.OPT1)){
                articleDao.addOpt1(id);
            }else if(opt.equals(ConstantUtil.OPT2)){
                articleDao.addOpt2(id);
            }else if(opt.equals(ConstantUtil.OPT3)){
                articleDao.addOpt3(id);
            }
        }
    }

    public boolean createArticle(String title,String describe,String content,Integer type,Integer scope,Integer status,
                                 String photo){
        Article article = new Article();
        article.setContent(content);
        article.setCreatetime(new Date());
        article.setDescribe(describe);
        article.setOpt1(0);
        article.setOpt2(0);
        article.setOpt3(0);
        article.setOpt4(0);
        article.setPhoto(photo);
        article.setScope(scope);
        article.setStatus(status);
        article.setTitle(title);
        return articleDao.insert(article);
    }
}
