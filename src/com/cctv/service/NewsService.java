package com.cctv.service;

import com.cctv.bean.News;
import dao.NewDao;

import java.util.List;


public class NewsService {

    private NewDao newDao;



    public NewsService(){
        newDao = new NewDao();
    }


//    增加新闻

    public boolean addNews(News news){
        return newDao.saveNews(news);
    }
//    查看新闻列表
    public List<News> getNewsList(){
        return newDao.getNewsList();
    }



}
