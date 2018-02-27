package com.wangchong.blog.dao;

import com.wangchong.blog.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleDao {

    /**
     * 条件查询
     * @param map
     * @return
     */
    List<Article> queryList(Map<String,Object> map);

    Article getById(Long id);

    boolean update(Map<String,Object> map);

    Long insert(Article obj);

    /**
     * 更改状态
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(Long id,Integer status);

    /**
     *
     * @param id
     * @return
     */
    boolean  addOpt1(Long id);

    /**
     *
     * @param id
     * @return
     */
    boolean  addOpt2(Long id);

    boolean  addOpt3(Long id);

    /**
     * 根据浏览量倒序查询
     * @return
     */
    List<Article> queryRankByOpt3();

    /**
     * 查询总文章数
     * @return
     */
    int getCount();








}
