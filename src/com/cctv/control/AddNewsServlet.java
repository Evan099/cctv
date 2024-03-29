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

import static java.lang.System.out;

@WebServlet("/AddNewsServlet")
public class AddNewsServlet extends HttpServlet {

    private NewsService newsService;

    @Override
    public void init() throws ServletException {
        super.init();
        newsService = new NewsService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String context = request.getParameter("context");
        String coverbg = request.getParameter("coverbg");
//        out.println(title);
//        out.println(context);

        News n=new News(title,context,coverbg);

        boolean news = newsService.addNews(n);

        JSONObject jsonObject = null;

        if(news == true){
            jsonObject = new JSONObject("{status:0}");
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
