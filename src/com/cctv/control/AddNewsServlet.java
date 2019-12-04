package com.cctv.control;

import com.cctv.service.NewsService;

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

        String context = request.getParameter("context");
        out.println(context);



    }

}
