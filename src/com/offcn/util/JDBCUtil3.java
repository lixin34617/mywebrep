package com.offcn.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil3 {

      private static DataSource ds;

      static{
          Properties pp = new Properties();
          FileInputStream fis = null;
          try {
              fis = new FileInputStream("src/druid.properties");
              pp.load(fis);
              ds = DruidDataSourceFactory.createDataSource(pp);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }


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
