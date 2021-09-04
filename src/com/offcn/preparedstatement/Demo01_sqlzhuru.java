package com.offcn.preparedstatement;

import com.offcn.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class Demo01_sqlzhuru {

    // 模拟登录：控制台输入用户名和密码，用户名密码正确打印登录成功，用户名密码错误打印登录失败！
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conn = JDBCUtil.getConnection();
            st = conn.createStatement();
            String sql = "select * from user where username='"+username+"' and password='"+password+"'";
            System.out.println(sql+"<<<>>>");

            rs = st.executeQuery(sql);

            if(rs.next()){  //登录成功
                System.out.println("登录成功！！！");
            }else{          // 登录失败
                System.out.println("登录失败~~~");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(rs,st,conn);
        }


    }
}
