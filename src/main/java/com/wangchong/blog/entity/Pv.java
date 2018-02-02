package com.wangchong.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Pv {

    private Long id;

    private Integer totalPv;

    private Integer todayPv;

    private Date createtime;

    private String ip;




}