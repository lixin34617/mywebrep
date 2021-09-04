package com.offcn.jdbc;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.offcn.util.JDBCUtil;
import org.junit.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo01 {

    @Test
    public void test2(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入姓名");
        String name = sc.nextLine();
        System.out.println("请输入年龄");
        String age = sc.nextLine();
        System.out.println("请输入生日");
        String birthday = sc.nextLine();

        Connection conn = null;
        Statement st = null;
        try{
            conn = JDBCUtil.getConnection();
            st = conn.createStatement();

            StringBuilder sb = new StringBuilder("insert into student values(null,'");
            sb.append(name).append("',").append(age).append(",'").append(birthday).append("')");
            String sql = sb.toString();

            int x = st.executeUpdate(sql);
            System.out.println(x);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,st,conn);
        }

    }


    @Test
    public void test1(){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conn = JDBCUtil.getConnection();
            st = conn.createStatement();
            String sql = "select * from student";

            rs = st.executeQuery(sql);

            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getDate(4));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            JDBCUtil.closeAll(rs,st,conn);
        }

    }
}
