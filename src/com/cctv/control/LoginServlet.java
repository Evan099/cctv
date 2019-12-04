package com.cctv.control;

import com.cctv.bean.User;
import com.cctv.service.UserService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.System.out;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        out.println(username);
        out.println(password);


        User user = userService.userLogin(username,password);

        User userInfo = new User(username,password);

        JSONObject jsonObject = null;

        if(user != null){
            jsonObject = new JSONObject("{status:0}");

            HttpSession session = request.getSession();


            session.setAttribute("user", userInfo);


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
