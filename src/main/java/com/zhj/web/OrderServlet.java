package com.zhj.web;

import com.zhj.pojo.Cart;
import com.zhj.pojo.User;
import com.zhj.service.OrderService;
import com.zhj.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OrderServlet", value = "/orderServlet")
public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginUser.getId();
//        调用orderService.createOrder(Cart,Userid);生成订单
        String to = loginUser.getEmail();
        String orderId = orderService.createOrder(cart, userId, to);
        if(orderId==null)
        {
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;setchar=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<script language=javascript>alert('库存不足');window.location='pages/cart/cart.jsp'</script>");
            return ;
        }
//        req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
//

}
