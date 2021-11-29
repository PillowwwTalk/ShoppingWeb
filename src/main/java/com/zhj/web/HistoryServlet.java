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
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Merchandise merchandise = merchandiseService.queryMerchandiseByid(id);
        HistoryItem historyItem = new HistoryItem(merchandise.getId(), merchandise.getName(), merchandise.getShop(), merchandise.getPrice());
        History history = (History) req.getSession().getAttribute("history");
        if (history == null) {
            history = new History();
            req.getSession().setAttribute("history", history);
        }
        history.addItem(historyItem);
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        History history= (History) req.getSession().getAttribute("history");
        if (history != null) {
            history.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        History history = (History) req.getSession().getAttribute("history");
        if (history != null) {
            history.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
