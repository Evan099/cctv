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
import static java.lang.System.setOut;

@WebServlet("/ShowNewDetailsServlet")
public class ShowNewDetailsServlet extends HttpServlet {

    private NewsService newsService;

    @Override
    public void init() throws ServletException {
        super.init();
        newsService = new NewsService();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("nid");


            int nid = Integer.parseInt(id);
            List<News> news = newsService.getNewsDetails(nid);

            out.println(news);


        JSONObject jsonObject = null;

        Map<String,Object> map = new HashMap<>();

            if(news != null){
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
