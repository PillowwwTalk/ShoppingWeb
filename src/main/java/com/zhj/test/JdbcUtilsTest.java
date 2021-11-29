package com.zhj.test;

import com.zhj.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
        JdbcUtils.close(connection);
    }
}
