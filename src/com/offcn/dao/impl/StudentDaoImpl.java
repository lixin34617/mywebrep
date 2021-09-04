package com.offcn.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.offcn.dao.StudentDao;
import com.offcn.util.JDBCUtil;
import com.offcn.bean.Student;

public class StudentDaoImpl implements StudentDao {
    @Override
    public int insertStudent(Student stu) {
        int result = 0;

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = JDBCUtil.getConnection();
            String sql = "insert into student values(null,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setObject(1,stu.getName());
            ps.setObject(2,stu.getAge());
            ps.setObject(3,stu.getBirthday());

            result = ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,ps,conn);
        }
        return result;
    }

    @Override
    public List<Student> findAllStudent() {

        List<Student> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "select * from student";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setBirthday(rs.getDate("birthday"));
                list.add(s);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(rs,ps,conn);
        }
        return list;
    }

    @Override
    public Student findStudentById(int id) {

        Student stu = null;

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
               stu = new Student();
               stu.setId(rs.getInt("id"));
               stu.setName(rs.getString("name"));
               stu.setAge(rs.getInt("age"));
               stu.setBirthday(rs.getDate("birthday"));
           }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(rs,ps,conn);
        }
        return stu;
    }

    @Override
    public List<Student> findStudentByName(String name) {
        List<Student> list = new ArrayList<>();
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
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setBirthday(rs.getDate("birthday"));
                list.add(s);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(rs,ps,conn);
        }
        return list;
    }
}
