package com.wangchong.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {

    private Long id;
    private Long articleId;
    private String content;
    private Integer status;
    private Date createtime;
    private String customerName;
    private String customerPhone;


}
