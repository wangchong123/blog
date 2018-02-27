package com.wangchong.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 后台登陆实体
 */
@Data
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
}
