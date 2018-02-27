package com.wangchong.blog.dao;

import com.wangchong.blog.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    /**
     * 后台登录
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);
}
