package com.cctv.dao;

import com.cctv.bean.User;
import com.cctv.common.Untils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User userLogin(String username,String password){
        Connection conn = Untils.getConnection();
        String sql = "select * from user where username =? and password = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2,password);
            rs = stmt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername("username");
                user.setPassword("password");
            }
        }catch (SQLException e){
            System.out.println("登录失败");
            e.printStackTrace();
        }finally {
            Untils.release(rs,stmt,conn);
        }

        return user;



    }


}
