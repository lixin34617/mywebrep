package com.offcn.preparedstatement;
import com.offcn.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.Connection;

public class Demo02_preparedstatementdenglu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtil.getConnection();

            String sql = "select * from user where username=? and password=?";
            ps = conn.prepareStatement(sql); // 创建PreparedStatement的对象时就要传入sql语句

            // 如果sql语句中有? 占位符  需要绑定（给? 绑定值）
            //ps.setString();
            ps.setObject(1,username);
            ps.setObject(2,password);

            //执行sql  DML-->executeUpdate   DQL-->executeQuery
            rs = ps.executeQuery();  // 因为在创建PreparedStatement时已经传入了sql，这里不需要再传入SQL了

/*            boolean flag =false;
            while(rs.next()){
                flag = true;
                break;
            }
            if(flag){
                System.out.println("登录成功");
            }else{
                System.out.println("登录失败");
            }
*/
            if(rs.next()){
                System.out.println("登录成功！！！");
            }else{
                System.out.println("登录失败~~~");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.closeAll(rs,ps,conn);
        }
    }
}
