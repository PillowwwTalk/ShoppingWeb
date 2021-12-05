package com.zhj.web;

import com.zhj.pojo.User;
import com.zhj.service.UserService;
import com.zhj.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //1. 判断用户名是否正确
        User loginUser = userService.login(new User(null, username, password,null));
        //null->登陆失败
        if(loginUser == null){
            //回显错误信息并且回显用户名
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //登陆成功
            req.getSession().setAttribute("user",loginUser);
            resp.sendRedirect("index.jsp");
        }
    }
}
