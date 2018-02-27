package com.wangchong.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * pv记录实体
 */
@Data
public class Pv {

    private Long id;

    private Date createtime;

    private String ip;


    //扩展字段
    /**
     * 总pv
     */
    private Integer totalPv;

    /**
     * 今日pv
     */
    private Integer todayPv;




}