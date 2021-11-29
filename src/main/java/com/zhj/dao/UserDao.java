package com.zhj.dao;

import com.zhj.pojo.User;
//import org.graalvm.compiler.lir.LIRInstruction;

public interface UserDao {



    /**
     * 注册时，根据用户名查询用户信息
     * @param username 用户名
     * @return
     */
    public User queryUserByUsername(String username);

    /**
     * 登录时，根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username,String password);
    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);

}
