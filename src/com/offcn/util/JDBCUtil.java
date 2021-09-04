package com.offcn.util;
import java.sql.*;
import java.util.ResourceBundle;

// JDBC工具类  封装两个方法 1：获取连接Connection  2：释放资源
public class JDBCUtil {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    // 加载驱动类 不需要每次操作都加载，只加载一次就够了
    static{
        // jdk1.5开始提供，可以读取properties文件中的内容。比Properties类操作简单
        //ResourceBundle rb = ResourceBundle.getBundle("com.offcn.config.db");
        ResourceBundle rb = ResourceBundle.getBundle("db");
        driver = rb.getString("db.driver");
        url = rb.getString("db.url");
        username=rb.getString("db.username");
        password=rb.getString("db.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取连接的方法
    public static Connection getConnection(){
        Connection conn = null;
        try {
             conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    // 释放资源的方法
    public static void closeAll(ResultSet rs, Statement st,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
