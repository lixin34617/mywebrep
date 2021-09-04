package com.offcn.dao.impl;

import com.offcn.bean.Book;
import com.offcn.bean.Student;
import com.offcn.dao.BookDao;
import com.offcn.util.JDBCUtil;
import com.offcn.util.JDBCUtil2;
import com.offcn.util.JDBCUtil3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> findAllBook() {
        List<Book> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //conn = JDBCUtil2.getConnection();
            conn = JDBCUtil3.getConnection();
            String sql = "select * from book";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Book b = new Book();
                b.setId(rs.getInt("id"));
                b.setBooknum(rs.getString("booknum"));
                b.setBookname(rs.getString("bookname"));
                b.setBookauthor(rs.getString("bookauthor"));
                b.setBookpublish(rs.getString("bookpublish"));
                b.setBookdate(rs.getDate("bookdate"));
                b.setBookprice(rs.getInt("bookprice"));
                list.add(b);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(rs,ps,conn);
        }
        return list;
    }

    @Override
    public int insertBook(Book b) {
        int result = 0;

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = JDBCUtil.getConnection();
            String sql = "insert into book values(null,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setObject(1,b.getBooknum());
            ps.setObject(2,b.getBookname());
            ps.setObject(3,b.getBookauthor());
            ps.setObject(4,b.getBookpublish());
            ps.setObject(5,b.getBookdate());
            ps.setObject(6,b.getBookprice());
            result = ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,ps,conn);
        }
        return result;
    }

    @Override
    public Book findBookById(int id) {
        Book b = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = JDBCUtil.getConnection();
            String sql = "select * from book where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                b = new Book();
                b.setId(rs.getInt("id"));
                b.setBooknum(rs.getString("booknum"));
                b.setBookname(rs.getString("bookname"));
                b.setBookauthor(rs.getString("bookauthor"));
                b.setBookpublish(rs.getString("bookpublish"));
                b.setBookdate(rs.getDate("bookdate"));
                b.setBookprice(rs.getInt("bookprice"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(rs,ps,conn);
        }
        return b;
    }

    @Override
    public int updateBook(Book b) {
        int result = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "update book set booknum=?,bookname=?,bookauthor=?,bookpublish=?,bookdate=?,bookprice=? where id=?";
            ps = conn.prepareStatement(sql);

            ps.setObject(1,b.getBooknum());
            ps.setObject(2,b.getBookname());
            ps.setObject(3,b.getBookauthor());
            ps.setObject(4,b.getBookpublish());
            ps.setObject(5,b.getBookdate());
            ps.setObject(6,b.getBookprice());
            ps.setObject(7,b.getId());
            result = ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,ps,conn);
        }
        return result;
    }

    @Override
    public int deleteBookById(int id) {
        int result = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "delete from book where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id);
            result = ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(null,ps,conn);
        }
        return result;
    }
}
