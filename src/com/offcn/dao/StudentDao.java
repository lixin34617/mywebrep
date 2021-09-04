package com.offcn.dao;
import com.offcn.bean.Student;

import java.util.List;

public interface StudentDao {

    // 增删改的方法可以 没有返回值，可以返回boolean 表示成功或失败
    // 也可以返回一数字，也是数据库中受影响的数据的条数

    // 1 添加
    public int insertStudent(Student stu);

    // 2 查询全部
    public List<Student> findAllStudent();

    // 3 根据id查询
    public Student findStudentById(int id);

    // 根据name模糊查询
    public  List<Student> findStudentByName(String name);
}
