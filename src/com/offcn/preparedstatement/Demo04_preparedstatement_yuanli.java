package com.offcn.preparedstatement;

public class Demo04_preparedstatement_yuanli {

    public static void main(String[] args) {

        /*
           PreparedStatement接口是Statement接口的子接口
           优点：
                1 增强程序可读性、可维护性（没有拼接sql了，代码看起来整洁）
                2 防止SQL注入问题（没有拼接sql了，用?占位和给?绑定值）
                3 对SQL语句有预编译的能力，提高效率！

           数据库在执行sql时，不是立即执行，而是要先对sql进行格式检查和编译，
           而且检查和编译的过程比较耗时！！！——如果减少了格式检查和编译过程，就会提高效率！

           ***** 使用Statement时（已添加数据为例） 添加多条数据
                Statement st = conn.createStatement();
                st.executeUpdate("insert into student values(1,'张三',20)");
                st.executeUpdate("insert into student values(2,'李四',21)");
                st.executeUpdate("insert into student values(3,'王五',22)");
                st.executeUpdate("insert into student values(4,'赵六',23)");
                st把4条sql分别发给数据库，数据库对4条sql 先检查格式和编译，再执行。
                如果添加10000条类似的数据，要检查和编译10000次。


          #####  使用PreparedStatement(添加数据)  添加多条数据

                 ps = conn.prepareStatement("insert into student values(?,?,?)");
                 ps.setObject(1,1);
                 ps.setObject(2,"张三");
                 ps.setObject(3,20);
                 ps.executeUpdate()

                 // 添加第2条时：重新给?绑定值
                 ps.setObject(1,2);
                 ps.setObject(2,"李四");
                 ps.setObject(3,21);
                 ps.executeUpdate()

                 ps.setObject(1,3);
                 ps.setObject(2,"王五");
                 ps.setObject(3,22);
                 ps.executeUpdate()

                 ps.setObject(1,4);
                 ps.setObject(2,"赵六");
                 ps.setObject(3,23);
                 ps.executeUpdate()


                PreparedStatement的工作过程是：  在创建对象时（conn.prepareStatement(sql)）,已经把
                                               sql语句传给了数据库，数据库对sql进行了格式的检查和编译（预编译），
                                               把sql编译成一个可执行的函数（函数存在数据库端，但是把函数的引用给了ps）

                                               ps给?绑定值其实就是在给函数赋参数，然后调用executeUpdate执行！！！

            只要使用的是同一个PreparedStatement对象（也就是一个ps，无论添加多少条数据），都只是在创建ps对象时检查和编译sql一次！！！

            预编译功能需要开启
                     如何开启？
                     jdbc:mysql:///db0811?
                     useUnicode=true&characterEncoding=utf8
                     &useSSL=false&serverTimezone=UTC&useServerPrepStmts=true

         */

    }
}
