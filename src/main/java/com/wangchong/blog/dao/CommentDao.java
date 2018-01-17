package com.wangchong.blog.dao;

import com.wangchong.blog.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao {

    boolean insert(Comment obj);

    boolean update(Comment obj);

    boolean delete(Long id);

    Comment getById(Long id);

}
