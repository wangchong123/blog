package com.wangchong.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {

    private Long id;

    private String ip;

    private Date createtime;
}
