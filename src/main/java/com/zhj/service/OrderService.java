package com.zhj.service;

import com.zhj.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId, String to);
}
