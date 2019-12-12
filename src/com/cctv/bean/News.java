package com.cctv.bean;

public class News {
    private int nid;
    private String title;
    private String context;

    //已知数据
    private int pageNum;
    private int pageSize;
    private int pageTotal;

    public News() {

    }

    public News(int pageNum,int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;

    }

    public News(String context) {
        this.context = context;
    }

    public News(int nid, String context) {
        this.nid = nid;
        this.context = context;
    }

    public News(String title,String context) {
        this.title = title;
        this.context = context;
    }

    public News(int nid, String title,String context) {
        this.nid = nid;
        this.title = title;
        this.context = context;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


//    分页
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
        pageTotal = pageTotal;
    }

//    @Override
//    public String toString() {
//        return "News{" +
//                "nid=" + nid +
//                ", title='" + title + '\'' +
//                ", context='" + context + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "News{" +
                "nid=" + nid +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", pageTotal=" + pageTotal +
                '}';
    }
}
