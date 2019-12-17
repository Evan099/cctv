package com.cctv.service;

import com.cctv.bean.User;
import com.cctv.dao.UserDao;

public class UserService {

    private UserDao userDao;
    public UserService(){
        userDao = new UserDao();
    }


    public User userLogin(String username,String password){
        return userDao.userLogin(username,password);
    }



}
