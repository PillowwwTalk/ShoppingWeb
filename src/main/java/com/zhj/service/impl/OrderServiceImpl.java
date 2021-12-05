package com.zhj.service.impl;

import com.zhj.dao.MerchandiseDao;
import com.zhj.dao.OrderDao;
import com.zhj.dao.OrderItemDao;
import com.zhj.dao.impl.MerchandiseDaoImpl;
import com.zhj.dao.impl.OrderDaoImpl;
import com.zhj.dao.impl.OrderItemDaoImpl;
import com.zhj.pojo.*;
import com.zhj.service.OrderService;
import com.zhj.web.Sendemail;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private MerchandiseDao merchandiseDao = new MerchandiseDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId, String to) {
        boolean mark = true;
//        通过遍历购物车的商品信息，逐一判断欲购买的商品总数是否超过了当前的库存，若超过了则给mark赋值为false
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            Merchandise merchandise = merchandiseDao.queryMerchandiseByid(cartItem.getId());
            if (merchandise.getStock() - cartItem.getCount() < 0) {
                mark = false;
                break;
            }
        }
        if (mark == true) {
            // 订单号===唯一性
            String orderId = System.currentTimeMillis() + "" + userId;
            // 创建一个订单对象
            Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
            // 保存订单
            orderDao.saveOrder(order);

            // 遍历购物车中每一个商品项转换成为订单项保存到数据库
            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                // 获取每一个购物车中的商品项
                CartItem cartItem = entry.getValue();
                // 转换为每一个订单项
                OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
                // 保存订单项到数据库
                orderItemDao.saveOrderItem(orderItem);
                // 更新库存和销量
                Merchandise merchandise = merchandiseDao.queryMerchandiseByid(cartItem.getId());
                merchandise.setSales(merchandise.getSales() + cartItem.getCount());
                merchandise.setStock(merchandise.getStock() - cartItem.getCount());
                merchandiseDao.updateMerchandise(merchandise);
            }
            String emailMsg = "您的订单号为" + orderId + "实际付款：" + cart.getTotalPrice();
            Sendemail.sendMail(to, emailMsg);
            // 清空购物车
            cart.clear();

            return orderId;
        }
        else {
           return null;
        }
    }
}

