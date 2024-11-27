/*
 * 连接数据库
 */



package com.example.demo.dao;
import java.sql.*;
import java.lang.*;
public class DatabaseConnection{
    private static String url = "jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=utf-8" ;     
    private static String user = "root";
    private static String password = "2024";
    private static 
    Connection con;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("加载数据库驱动成功!");
            return  DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            System.err.println("找不到驱动程序类 ，加载驱动失败！");   
            e.printStackTrace();
        }
        catch (Exception e) {
            System.err.println("获取数据库连接失败！");
            e.printStackTrace();
        }
        return null;
        }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("数据库连接已关闭!");
            } catch (SQLException e) {
                System.out.println("关闭连接时发生错误!");
                e.printStackTrace();
            }
        }
    }

}