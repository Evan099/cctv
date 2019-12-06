package com.cctv.service;

import com.cctv.bean.News;
import dao.NewDao;

import java.util.List;


public class NewsService {

    private NewDao newDao;



    public NewsService(){
        newDao = new NewDao();
    }


//      增加单条新闻

    public boolean addNews(News news){
        return newDao.saveNews(news);
    }
//      查看所有新闻
    public List<News> getNewsList(){
        return newDao.getNewsList();
    }

//      查询单条新闻
    public List<News> getNewsDetails(int nid){
        return newDao.getNewDetail(nid);
    }



}
