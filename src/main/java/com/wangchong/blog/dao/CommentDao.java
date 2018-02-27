package com.wangchong.blog.dao;

import com.wangchong.blog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentDao {

    boolean insert(Comment obj);

    boolean delete(Long id);

    Comment getById(Long id);

    /**
     * 条件查询
     * @param map
     * @return
     */
    List<Comment> queryList(Map<String,Object> map);

    /**
     * 查询文章评论数
     * @param map
     * @return
     */
    int queryListCount(Map<String,Object> map);

    /**
     * 更改状态
     * @param id
     * @param status
     * @return
     */
    boolean update(Long id,Integer status);

}
