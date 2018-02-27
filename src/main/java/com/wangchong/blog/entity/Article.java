package com.wangchong.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//文章实体类
@Data
public class Article implements Serializable{

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String describe;

    /**
     * 正文
     */
    private String content;

    /**
     * 状态 0-未审核 1-有效 -1-删除
     */
    private Integer status;

    /**
     * 封面图
     */
    private String photo;

    /**
     * 赞
     */
    private Integer opt1;

    /**
     *评论
     */
    private Integer opt2;

    /**
     *浏览
     */
    private Integer opt3;

    /**
     *踩
     */
    private Integer opt4;
    private Date createtime;
    private Integer scope;

    /**
     * 分类
     */
    private Long type;


    //扩展字段
    /**
     * 分类名
     */
    private String typeName;

}
