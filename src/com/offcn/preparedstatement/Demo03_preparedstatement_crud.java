package com.offcn.preparedstatement;

import com.offcn.util.JDBCUtil;
import org.junit.Test;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Demo03_preparedstatement_crud {

    @Test
    public void test7(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "delete from student";
            ps = conn.prepareStatement(sql);
            int x = ps.executeUpdate();
            System.out.println(x+"<<<>>>");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,ps,conn);
        }
    }

    @Test
    public void test6(){  //根据输入的id删除
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的id");
        String id = sc.nextLine();

        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "delete from student where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id);

            int x = ps.executeUpdate();
            System.out.println(x+"<<<>>>");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,ps,conn);
        }

    }


    @Test
    public void test5(){  // 根据name模糊查询
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要查询姓名");
        String name = sc.nextLine();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = JDBCUtil.getConnection();
            String sql = "select * from student where name like ?";

            ps = conn.prepareStatement(sql);
            ps.setObject(1,"%"+name+"%");

            rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getInt("age")+"\t"+rs.getDate("birthday"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(rs,ps,conn);
        }

    }


    @Test
    public void test4(){  // 根据输入的id查询
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要查询的id");
        String id = sc.nextLine();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "select * from student where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id);

            rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getInt("age")+"\t"+rs.getDate("birthday"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(rs,ps,conn);
        }

    }


    @Test
    public void test3(){  // 查询全部
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "select * from student";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+rs.getInt("age")+rs.getDate("birthday"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(rs,ps,conn);
        }
    }

    @Test
    public void test2(){  // 更新
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要更新的id");
        String id = sc.nextLine();
        System.out.println("请输入姓名");
        String name = sc.nextLine();
        System.out.println("请输入年龄");
        String age = sc.nextLine();
        System.out.println("请输入生日");
        String birthday = sc.nextLine();

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = JDBCUtil.getConnection();
            String sql = "update student set name=?,age=?,birthday=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,name);
            ps.setObject(2,age);
            ps.setObject(3,birthday);
            ps.setObject(4,id);

            int x = ps.executeUpdate();
            System.out.println(x+"<<<>>>");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,ps,conn);
        }
    }


    @Test
    public void test1(){  //测试添加
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入姓名");
        String name = sc.nextLine();
        System.out.println("请输入年龄");
        String age = sc.nextLine();
        System.out.println("请输入生日");
        String birthday = sc.nextLine();

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = JDBCUtil.getConnection();
            String sql = "insert into student values(null,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,name);
            ps.setObject(2,age);
            ps.setObject(3,birthday);
            int x = ps.executeUpdate();
            System.out.println(x+"<<<>>>");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,ps,conn);
        }
    }
}
