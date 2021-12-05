package com.zhj.web;
import com.zhj.pojo.History;
import com.zhj.pojo.HistoryItem;
import com.zhj.pojo.Merchandise;
import com.zhj.service.MerchandiseService;
import com.zhj.service.impl.MerchandiseServiceImpl;
import com.zhj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HistoryServlet", value = "/historyServlet")
public class HistoryServlet extends BaseServlet{
    MerchandiseService merchandiseService = new MerchandiseServiceImpl();
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取商品id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//        根据id查询商品信息
        Merchandise merchandise = merchandiseService.queryMerchandiseByid(id);
//        定义一个hitoryitem存储一个商品的浏览信息
        HistoryItem historyItem = new HistoryItem(merchandise.getId(), merchandise.getName(), merchandise.getShop(), merchandise.getPrice());
//        从Session中提取history
        History history = (History) req.getSession().getAttribute("history");
        if (history == null) {
            history = new History();
            req.getSession().setAttribute("history", history);
        }
//        将浏览信息添加到Session中
        history.addItem(historyItem);
//        重定向返回首页
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取商品id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//        获取Session中的history
        History history= (History) req.getSession().getAttribute("history");
        if (history != null) {
//            删除浏览信息
            history.deleteItem(id);
//            重定向返回首页
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        History history = (History) req.getSession().getAttribute("history");
        if (history != null) {
//            清空浏览信息
            history.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
