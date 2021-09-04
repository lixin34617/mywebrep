
package com.offcn.test;

import com.offcn.dao.StudentDao;
import com.offcn.dao.impl.StudentDaoImpl;
import com.offcn.util.DateUitl;
import org.junit.Test;
import com.offcn.bean.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentTest {

    @Test
    public void test4(){
        StudentDao dao = new StudentDaoImpl();
        List<Student> list = dao.findStudentByName("三");
        for(Student s:list){
            System.out.println(s);
        }
    }


    @Test
    public void test3(){

        StudentDao dao = new StudentDaoImpl();

        Student s = dao.findStudentById(11);
        System.out.println(s);

    }

    @Test
    public void test2(){

        StudentDao dao = new StudentDaoImpl();
        List<Student> list = dao.findAllStudent();

        for(Student s:list){
            System.out.println(s);
        }

    }


    @Test
    public void test1(){

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入姓名");
        String name = sc.nextLine();
        System.out.println("请输入年龄");
        String age = sc.nextLine();
        System.out.println("请输入生日");
        String birthday = sc.nextLine();

        Student s = new Student();
        s.setName(name);
        s.setAge(Integer.parseInt(age));
        s.setBirthday(DateUitl.stringToDate(birthday));

        StudentDao dao = new StudentDaoImpl();
        int x = dao.insertStudent(s);
        System.out.println(x);

    }
}
