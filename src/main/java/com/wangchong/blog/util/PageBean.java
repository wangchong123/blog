package com.wangchong.blog.util;

public class PageBean {

    private Integer currentPage = 1;//当前页
    private Integer pageSize = 0;//每页条数
    private Integer totalSize = 0;//总条数
    private Integer totalPage = 0;//总页数
    private Integer startSize=0;//起始条数
    private Integer previousPage;//上一页
    private Integer nextPage;//下一页

    public PageBean(){

    }

    public PageBean(Integer currentPage,Integer pageSize,Integer totalSize){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getTotalPage() {
        if(this.totalPage!=0){
            return this.totalPage;
        }
        return this.totalSize % this.pageSize == 0 ? (this.totalSize/this.pageSize) : (this.totalSize/this.pageSize)+1;

    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartSize() {
        return startSize;
    }

    public void setStartSize(Integer startSize) {
        this.startSize = startSize;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
}
