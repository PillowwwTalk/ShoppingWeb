package com.zhj.dao.impl;

import com.zhj.dao.UserDao;
import com.zhj.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`, `username`,`password`,`email` from shopping where username= ?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id` , `username`, `password`,`email` from shopping where username= ? and password = ?";
        return queryForOne(User.class,sql,username, password);
    }
    @Override
    public int saveUser(User user) {
        String sql = "insert into shopping(`username`, `password`, `email`) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}


