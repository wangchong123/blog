package com.wangchong.blog.service;

import com.wangchong.blog.dao.ArticleDao;
import com.wangchong.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public List<Article> queryArticleList(Long type){
        Map<String,Object> map = new HashMap<>();
        if(type != null){
            map.put("type",type);
        }
        return articleDao.queryList(map);
    }

    public Article getArticle(Long id){
        if(id != null){
            return articleDao.getById(id);
        }
        return null;
    }
}
