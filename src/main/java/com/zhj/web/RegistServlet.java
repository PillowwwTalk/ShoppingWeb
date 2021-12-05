package com.zhj.web;

import com.sun.mail.iap.Response;
import com.zhj.pojo.User;
import com.zhj.service.UserService;
import com.zhj.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


@WebServlet(name = "RegistServlet", value = "/registServlet")
public class RegistServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取Session中的验证码,并立即从Session中删除
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1. 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //2. 判断验证码是否正确
        if (token!=null&&token.equalsIgnoreCase(code)) // 正确
        {   if(userService.existsUsername(username)){
            // 若用户名存在
            //回显错误信息和邮箱
            req.setAttribute("msg", "用户名已存在!");
            req.setAttribute("email", email);
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        } else{
            // 若用户名不存在
            userService.registerUser(new User(null, username , password, email));
            System.out.println("用户名不存在");
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;setchar=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<script language=javascript>alert('注册成功');window.location='index.jsp'</script>");
        }

        }else{
//            验证码错误
            System.out.println("验证码错误");
//            回显错误信息
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("email", email);
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
