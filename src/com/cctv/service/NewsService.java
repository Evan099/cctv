package com.cctv.service;

import com.cctv.bean.News;
import dao.NewDao;
import org.omg.CORBA.PUBLIC_MEMBER;

public class NewsService {

    private NewDao newDao;

    public NewsService(){
        newDao = new NewDao();
    }


//    增加新闻

    public boolean addNews(News news){
        return newDao.saveNews(news);

    }



}
