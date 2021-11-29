package com.zhj.web;

import com.zhj.pojo.Merchandise;
import com.zhj.pojo.Page;
import com.zhj.service.MerchandiseService;
import com.zhj.service.impl.MerchandiseServiceImpl;
import com.zhj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "MerchandiseServlet", value = "/manager/merchandiseServlet")
public class MerchandiseServlet extends BaseServlet{
    private MerchandiseService merchandiseService = new MerchandiseServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Page<Merchandise> page = merchandiseService.page(pageNo,pageSize);
        page.setUrl("manager/merchandiseServlet?action=page");
        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/merchandise_manager.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
    Merchandise merchandise = WebUtils.copyParamToBean(req.getParameterMap(),new Merchandise());
    merchandiseService.addMerchandise(merchandise);
    resp.sendRedirect(req.getContextPath()+"/manager/merchandiseServlet?action=page&pageNo="+pageNo);
    }

    protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        merchandiseService.delMerchandise(id);
        resp.sendRedirect(req.getContextPath()+"/manager/merchandiseServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }
    protected void getMerchandise(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Merchandise merchandise = merchandiseService.queryMerchandiseByid(id);
        req.setAttribute("merchandise", merchandise) ;
        req.getRequestDispatcher("/pages/manager/merchandise_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Merchandise merchandise = WebUtils.copyParamToBean(req.getParameterMap(), new Merchandise());
       merchandiseService.updateMerchandise(merchandise);
        resp.sendRedirect(req.getContextPath()+"/manager/merchandiseServlet?action=page&pageNo="+ req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Merchandise>  merchandises= merchandiseService.queryMerchandise();
        req.setAttribute("merchandises", merchandises);
        req.getRequestDispatcher("/pages/manager/merchandise_manager.jsp").forward(req,resp);
    }
}
