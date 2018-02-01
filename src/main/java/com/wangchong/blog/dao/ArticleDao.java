package com.wangchong.blog.dao;

import com.wangchong.blog.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleDao {

    List<Article> queryList(Map<String,Object> map);

    Article getById(Long id);

    boolean update(Map<String,Object> map);

    Long insert(Article obj);

    boolean updateStatus(Long id,Integer status);

    boolean  addOpt1(Long id);

    boolean  addOpt2(Long id);

    boolean  addOpt3(Long id);

    /**
     * 根据浏览量倒序查询
     * @return
     */
    List<Article> queryRankByOpt3();








}
