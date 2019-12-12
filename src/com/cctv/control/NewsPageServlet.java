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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

@WebServlet("/NewsPageServlet")
public class NewsPageServlet extends HttpServlet {

    private NewsService newsService;

    @Override
    public void init() throws ServletException {
        super.init();
        newsService = new NewsService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int pageNum = Integer.valueOf(request.getParameter("pageNum"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));

        out.println(pageNum);
        out.println(pageSize);

        News news = new News(pageNum,pageSize);

        List<News> newsRs = newsService.getNewsPage(news);


                    out.println(newsRs);


        JSONObject jsonObject = null;
        if(newsRs != null){
            response.setContentType("text/html;charset=utf-8");

            Map<String,Object> m = new HashMap<>();
            m.put("data",newsRs);
            m.put("status","0");
            m.put("message","success");

            jsonObject = new JSONObject(m);

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
