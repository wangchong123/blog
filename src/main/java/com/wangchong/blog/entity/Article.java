package com.wangchong.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Article implements Serializable{

    private Long id;
    private String title;
    private String describe;
    private String content;
    private Integer status;
    private String photo;
    private Integer opt1; //赞
    private Integer opt2; //评论
    private Integer opt3; //浏览
    private Integer opt4; //踩
    private Date createtime;
    private Integer scope;
    private Long type;

}
