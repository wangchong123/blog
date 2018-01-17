package com.wangchong.blog.util;

public class PageBean {

    private Integer currentPage;//当前页
    private Integer pageSize;//每页条数
    private Integer totalSize;//总条数
    private Integer totalPage;//总页数
    private Integer startSize;//起始条数
    private Integer previousPage;//上一页
    private Integer nextPage;//下一页

    public PageBean(){

    }

    public PageBean(Integer pageSize,Integer totalSize){
        if(pageSize <= 0) pageSize = 1;
        if(totalSize < 0) totalSize = 0;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.totalPage = (totalSize % pageSize) == 0 ? (totalSize / pageSize) : (totalSize / pageSize) + 1;
    }

    public PageBean(Integer currentPage,Integer pageSize,Integer totalSize){
        this.startSize = (currentPage-1)*pageSize;
    }




}
