package com.cctv.control;

import com.cctv.bean.News;
import com.cctv.service.NewsService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

@WebServlet("/ShowNewListServlet")
public class ShowNewListServlet extends HttpServlet {

    private NewsService newsService;

    @Override
    public void init() throws ServletException {
        super.init();
        newsService = new NewsService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        List<News> news = newsService.getNewsList();

//        out.println(news);

        JSONObject jsonObject = null;


        if(news != null){
            Map<String,Object> map=new HashMap<>();
            map.put("data",news);
            map.put("status","0");
            map.put("message","success");
            jsonObject = new JSONObject(map);
        }else{
            jsonObject = new JSONObject("{status:1}");
        }
        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));


    }


    @Override
    public void destroy() {
        super.destroy();
    }



}
