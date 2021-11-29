package com.zhj.service.impl;

import com.zhj.dao.UserDao;
import com.zhj.dao.impl.UserDaoImpl;
import com.zhj.pojo.User;
import com.zhj.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username) == null)
        { //说明没查到，表示可用
            return false;
        }
        return true;
    }
}
