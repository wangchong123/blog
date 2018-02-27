package com.wangchong.blog.service;

import com.wangchong.blog.dao.UserDao;
import com.wangchong.blog.entity.User;
import com.wangchong.blog.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 后台登陆
     * @param username
     * @param password
     * @return
     */
    public User login(String username,String password){

        return userDao.login(username, Md5Util.getMD5(password));
    }
}
