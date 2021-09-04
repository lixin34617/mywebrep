package com.offcn.util;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtil2 {

       private static DataSource ds = new ComboPooledDataSource();  // 默认读取default-config中的配置
       //private static DataSource ds = new ComboPooledDataSource("offcn");
       public static Connection getConnection(){
           Connection conn = null;
           try {
               conn = ds.getConnection();
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
