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

//      删除一条新闻
    public int delOneNew(int nid){
        return newDao.delOneNew(nid);
    }
//      修改一条新闻
    public boolean changeOneNew(News news){
        return newDao.changeOneNew(news);
    }

//      分页新闻查询
    public List<News> getNewsPage(News news){
        return newDao.getNewsPage(news);
    }



}
