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

@WebServlet("/UpdateOneNewServlet")
public class UpdateOneNewServlet extends HttpServlet {

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
        String title = request.getParameter("title");
        String context = request.getParameter("context");

        News news = new News(nid,title,context);

        boolean isChange = newsService.changeOneNew(news);

        JSONObject jsonObject = null;
//        out.println("修改结果"+isChange);

        if(isChange == true){
            response.setContentType("text/html;charset=utf-8");

            jsonObject = new JSONObject("{status:'0'}");
        }else{
            jsonObject = new JSONObject("{status:'1'}");
        }
        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));


    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
