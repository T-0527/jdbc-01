package com.aaa.dao;

import com.aaa.entity.Dept;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeptDao {
    public int insert(String name){
        Connection con=null;
        PreparedStatement ps=null;
        try{
            //1、获取连接对象
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ls","root","root");
            //2、获取执行sql语句的对象
            String sql="insert into dept values(null,?)";
            ps=con.prepareStatement(sql);
            //2.2 为占位符赋值
            ps.setString(1,name);
            //3、执行sql语句
            int row=ps.executeUpdate();
            return row;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //删除部门
    public int delete(int id){
        Connection con=null;
        PreparedStatement ps=null;
        try{
            //1、获取连接对象
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ls","root","root");
            //2、获取执行sql语句的对象
            String sql="delete from dept where id=?";
            ps=con.prepareStatement(sql);
            //2.2 为占位符赋值
            ps.setInt(1,id);
            //3、执行sql语句
            int row=ps.executeUpdate();
            return row;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //修改部门
    public int update(int id,String name){
        Connection con=null;
        PreparedStatement ps=null;
        try{
            //1、获取连接对象
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ls","root","root");
            //2、获取执行sql语句的对象
            String sql="update dept set name=? where id=?";
            ps=con.prepareStatement(sql);
            //2.2 为占位符赋值
            ps.setString(1,name);
            ps.setInt(2,id);
            //3、执行sql语句
            int row=ps.executeUpdate();
            return row;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //查询部门
    public List<Dept> findAll(){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Dept> depts=new ArrayList<>();
        try{
            //1、获取连接对象
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ls","root","root");
            //2、获取执行sql语句的对象
            String sql="select * from dept";
            ps=con.prepareStatement(sql);
            //3、执行sql语句
            rs=ps.executeQuery();
            //4、处理结果
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                Dept d=new Dept();
                d.setId(id);
                d.setName(name);
                depts.add(d);
            }
            return depts;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(rs!=null){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
