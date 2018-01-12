package com.wangchong.blog.dao;

import com.wangchong.blog.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getByName(String username,String password);
}
