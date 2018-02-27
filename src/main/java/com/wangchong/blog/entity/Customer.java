package com.wangchong.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * 访客实体
 */
@Data
public class Customer {

    private Long id;

    /**
     * 访问ip
     */
    private String ip;

    private Date createtime;
}
