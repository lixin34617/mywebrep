package com.offcn.test;

import com.offcn.bean.Book;
import com.offcn.dao.BookDao;
import com.offcn.dao.impl.BookDaoImpl;
import com.offcn.util.DateUitl;

import java.util.List;
import java.util.Scanner;

public class BookTest {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        while(true){
            printMainMenu();
            String str = sc.nextLine();

            if("A".equalsIgnoreCase(str)){  // 查询全部
                findAll();
            }else if("B".equalsIgnoreCase(str)){  // 添加
                addBook();
                findAll();
            }else if("C".equalsIgnoreCase(str)){ // 修改
                modifyBook();
                findAll();
            }else if("D".equalsIgnoreCase(str)){  // 删除
                deleteBook();
                findAll();
            }else if("E".equalsIgnoreCase(str)){  // 退出
                 System.exit(0);
            }else{
                System.out.println("请输入正序选项");
            }
        }
    }

    public static void printMainMenu(){
        System.out.println("[A]查询所有书籍   [B]增加书籍   [C]修改书籍   [D]删除书籍 [E]退出");
        System.out.println("请选择操作：");
    }

    public static void findAll(){
        BookDao dao = new BookDaoImpl();
        List<Book> list = dao.findAllBook();
        for(Book b:list){
            System.out.println(b);
        }
    }

    public static void addBook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入书籍编号");
        String booknum = sc.nextLine();
        System.out.println("请输入书籍名称");
        String bookname = sc.nextLine();
        System.out.println("请输入书籍作者");
        String bookauthor = sc.nextLine();
        System.out.println("请输入书籍出版社");
        String bookpublish = sc.nextLine();
        System.out.println("请输入书籍出版日期");
        String bookdate = sc.nextLine();
        System.out.println("请输入书籍价格");
        String bookprice = sc.nextLine();

        Book b = new Book();
        b.setBooknum(booknum);
        b.setBookname(bookname);
        b.setBookauthor(bookauthor);
        b.setBookpublish(bookpublish);
        b.setBookdate(DateUitl.stringToDate(bookdate));
        b.setBookprice(Integer.parseInt(bookprice));

        BookDao dao = new BookDaoImpl();
        int result = dao.insertBook(b);
        if(result!=0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    public static void modifyBook(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("请输入要修改的书籍id");
            String id = sc.nextLine();
            BookDao dao = new BookDaoImpl();
            Book b = dao.findBookById(Integer.parseInt(id));
            if(b==null){
                System.out.println("请输入正确的书籍id");
            }else{
                System.out.println("请输入要更新的字段");
                System.out.println("【A】编号 【B】书名 【C】作者 【D】出版社 【E】出版时间 【F】价格");
                String str = sc.nextLine();
                if("A".equalsIgnoreCase(str)){
                    System.out.println("请输入编号");
                    String booknum = sc.nextLine();
                    b.setBooknum(booknum);
                }else if("B".equalsIgnoreCase(str)){
                    System.out.println("请输入书名");
                    String bookname = sc.nextLine();
                    b.setBookname(bookname);
                }else if("C".equalsIgnoreCase(str)){
                    System.out.println("请输入作者");
                    String bookauthor = sc.nextLine();
                    b.setBookauthor(bookauthor);
                }else if("D".equalsIgnoreCase(str)){
                    System.out.println("请输入出版社");
                    String bookpublish = sc.nextLine();
                    b.setBookpublish(bookpublish);
                }else if("E".equalsIgnoreCase(str)){
                    System.out.println("请输入出版时间");
                    String bookdate = sc.nextLine();
                    b.setBookdate(DateUitl.stringToDate(bookdate));
                }else if("F".equalsIgnoreCase(str)){
                    System.out.println("请输入价格");
                    String bookprice = sc.nextLine();
                    b.setBookprice(Integer.parseInt(bookprice));
                }else{
                    System.out.println("请输入正确的字段选项");
                }

                int result = dao.updateBook(b);
                if(result>0){
                    System.out.println("更新成功");
                }else{
                    System.out.println("更新失败");
                }
                break;  // 跳出循环（更新操作）
            }
        }
    }

    public static void deleteBook(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("请输入要删除的书籍id");
            String id = sc.nextLine();

            BookDao dao = new BookDaoImpl();
            Book b = dao.findBookById(Integer.parseInt(id));
            if(b==null){
                System.out.println("请输入正确的书籍id");
            }else{
                int result = dao.deleteBookById(b.getId());
                if(result>0){
                    System.out.println("删除成功！");
                }else{
                    System.out.println("删除失败");
                }
                break;
            }
        }
    }
}
