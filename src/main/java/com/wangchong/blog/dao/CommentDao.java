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

    List<Comment> queryList(Map<String,Object> map);

    int queryListCount(Map<String,Object> map);

}
