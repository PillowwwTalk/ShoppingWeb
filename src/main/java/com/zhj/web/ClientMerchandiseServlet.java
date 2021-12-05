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

@WebServlet(name = "ClientMerchandiseServlet", value = "/client/merchandiseServlet")
public class ClientMerchandiseServlet extends BaseServlet{
    private MerchandiseService merchandiseService = new MerchandiseServiceImpl() ;
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //2 调用MerchandiseService.page(pageNo，pageSize)：Page对象

        Page<Merchandise> page = merchandiseService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder sb = new StringBuilder("client/merchandiseServlet?action=pageByPrice");
        // 如果有最小价格的参数,追加到分页条的地址参数中
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        // 如果有最大价格的参数,追加到分页条的地址参数中
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/merchandise_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用MerchandiseService.page(pageNo，pageSize)：Page对象

        Page<Merchandise> page = merchandiseService.page(pageNo,pageSize);
        page.setUrl("client/merchandiseServlet?action=page");
        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
