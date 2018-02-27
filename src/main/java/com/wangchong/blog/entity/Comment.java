package com.wangchong.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论实体
 */
@Data
public class Comment implements Serializable {

    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 状态 0-未审核 1-有效 -1-删除
     */
    private Integer status;
    private Date createtime;

    /**
     * 用户昵称
     */
    private String customerName;

    /**
     * 联系方式
     */
    private String customerPhone;


    //扩展字段
    /**
     * 文章标题
     */
    private String title;


}
