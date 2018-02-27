package com.wangchong.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类实体
 */
@Data
public class Type implements Serializable {

    private Long id;
    private String name;
    private Integer status;
    private Integer scope;

    //扩展
    /**
     * 文章数
     */
    private Integer count;
}
