package com.offcn.jdbc;

import com.offcn.util.JDBCUtil;
import org.junit.Test;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo02_zhuanzhang {

    @Test
    public void test2(){  // 添加事务控制

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入转出账户");
        String idout = sc.nextLine();
        System.out.println("请输入转入账户");
        String idin = sc.nextLine();
        System.out.println("请输入钱数");
        String money = sc.nextLine();

        Connection conn = null;
        Statement st = null;

        try{
            conn = JDBCUtil.getConnection();

            conn.setAutoCommit(false);  // 设置为手动提交事务

            st = conn.createStatement();

            String sql1 = "update account set money=money-"+money+" where id="+idout;  // 减钱
            String sql2 = "update account set money=money+"+money+" where id="+idin;   // 加钱

            int x = st.executeUpdate(sql1);

            int result = 10/0;  // 虽然sql1 执行成功了，但事务并没有提交，进入到catch中，catch做了回滚。

            int y = st.executeUpdate(sql2);

            conn.commit();  // 提交事务

            System.out.println(x+"<<<>>>");
            System.out.println(y+"<<<>>>");

        }catch(Exception e){
            try {
                conn.rollback();  // 回滚
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,st,conn);
        }
    }

    @Test
    public void test1(){  // 不加事务控制的代码

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入转出账户");
        String idout = sc.nextLine();
        System.out.println("请输入转入账户");
        String idin = sc.nextLine();
        System.out.println("请输入钱数");
        String money = sc.nextLine();

        Connection conn = null;
        Statement st = null;

        try{
            conn = JDBCUtil.getConnection();
            st = conn.createStatement();

            String sql1 = "update account set money=money-"+money+" where id="+idout;  // 减钱
            String sql2 = "update account set money=money+"+money+" where id="+idin;   // 加钱

            int x = st.executeUpdate(sql1);
            int result = 10/0;  // 由于Connection是自动提交事务的，每执行一个sql 就是一个事务
            int y = st.executeUpdate(sql2);

            System.out.println(x+"<<<>>>");
            System.out.println(y+"<<<>>>");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,st,conn);
        }
    }
}
