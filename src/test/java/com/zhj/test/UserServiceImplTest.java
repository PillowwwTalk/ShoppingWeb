package com.zhj.test;

import com.zhj.pojo.User;
import com.zhj.service.UserService;
import com.zhj.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "zhj2", "1234567","111111@qq.com"));
    }
}