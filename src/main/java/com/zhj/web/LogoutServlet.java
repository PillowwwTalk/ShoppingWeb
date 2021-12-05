package com.zhj.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logoutServlet")
public class LogoutServlet extends BaseServlet {

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      清空Session，实现注销功能
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }
}
