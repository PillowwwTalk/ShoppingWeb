package com.zhj.dao.impl;

import com.zhj.dao.OrderDao;
import com.zhj.pojo.Order;
import com.zhj.pojo.User;
import com.zhj.web.BaseServlet;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

}
