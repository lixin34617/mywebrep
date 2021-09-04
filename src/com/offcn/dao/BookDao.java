package com.offcn.dao;

import com.offcn.bean.Book;

import java.util.List;

public interface BookDao {

    public List<Book> findAllBook();

    public int insertBook(Book book);

    public Book findBookById(int id);

    public int updateBook(Book b);

    public int deleteBookById(int id);
}
