package com.cctv.control;

import com.cctv.bean.PageEntity;
import com.cctv.service.NewsService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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

        out.println("前端传给后端参数:pageNum:"+pageNum);
        out.println("前端传给后端参数:pageSize:"+pageSize);

        PageEntity PageEntity = new PageEntity(pageNum,pageSize);
//        News news = new News();

        PageEntity newsRs = newsService.getNewsPage(PageEntity);


                    out.println(newsRs);


        JSONObject jsonObject = null;
        if(newsRs != null){
            response.setContentType("text/html;charset=utf-8");

            Map<String,Object> m = new HashMap<>();
            m.put("data",newsRs.getNewsList());
            m.put("status","0");
            m.put("total",newsRs.getPageTotal());
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
