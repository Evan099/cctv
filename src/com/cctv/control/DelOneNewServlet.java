package com.cctv.control;

import com.cctv.service.NewsService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.System.out;

@WebServlet("/DelOneNewServlet")
public class DelOneNewServlet extends HttpServlet {


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
        out.println(nid);

        int isDel = newsService.delOneNew(nid);
        JSONObject jsonObject = null;
//        out.println("删除结果："+isDel);
        if(isDel == 1){
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
