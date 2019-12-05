package com.cctv.bean;

public class News {
    private int nid;
    private String title;
    private String context;

    public News() {

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

    @Override
    public String toString() {
        return "News{" +
                "nid=" + nid +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
