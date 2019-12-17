package com.cctv.bean;

import java.util.List;

public class PageEntity {
    //已知数据
    private int pageNum;
    private int pageSize;
    private int pageTotal;
    private List<News> newsList;

    public PageEntity(int pageNum,int pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageEntity(int pageTotal){
        this.pageTotal = pageTotal;
    }

    //    set get
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

//    tostring


    @Override
    public String toString() {
        return "PageEntity{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", pageTotal=" + pageTotal +
                ", newsList=" + newsList +
                '}';
    }




}
