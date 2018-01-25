package com.wangchong.blog.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Type implements Serializable {

    private Long id;
    private String name;
    private Integer status;
    private Integer scope;

    private Integer count;
}
