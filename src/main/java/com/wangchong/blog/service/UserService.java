package com.wangchong.blog.service;

import com.wangchong.blog.dao.UserDao;
import com.wangchong.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User login(String username,String password){
        return userDao.getByName(username,password);
    }
}
