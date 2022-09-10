package com.keyan.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.keyan.dao.AdminDao;
import com.keyan.pojo.Admin;

public class AdminDaoImpl implements AdminDao{
	//根据用户名和密码查询用户信息
	@Override
	public Admin checkAdminLoginDao(String aname, String pwd) {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明变量
		Admin a = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");//mysql-conncctor-java-8.0
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan", "root", "admin");//mysql-conncctor-java-5.1
			//创建sql命令
			String sql = "select * from t_admin where aname=? and pwd=?";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, aname);
			ps.setString(2, pwd);
			//执行sql
			rs = ps.executeQuery();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				a = new Admin();
				a.setaid(rs.getInt("aid"));
				a.setaname(rs.getString("aname"));
				a.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//关闭资源
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//返回结果
		System.out.println("测试管理员表查询情况" + a);
		return a;
	}

	//根据管理员id修改管理员密码
	@Override
	public int adminChangePwdDso(int anid, String newPwd) {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		//创建变量
		int index = -1;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建SQL命令
			String sql = "update t_admin set pwd=? where uid=?";
			//创建SQL命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, newPwd);
			ps.setInt(2, anid);
			//执行
			index = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("这是数据库拿到的" + index);
		}finally {
			//关闭资源
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//返回结果
		return index;
	}

	//获取所有用户信息
	@Override
	public List<Admin> adminShowDao() {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明allUser集合
		List<Admin> allAdmin = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");//mysql-conncctor-java-8.0
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan", "root", "admin");//mysql-conncctor-java-5.1
			//创建sql命令
			String sql = "select * from t_user";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//执行sql
			rs = ps.executeQuery();
			//给集合赋值
			allAdmin = new ArrayList<Admin>();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				Admin a = new Admin();
				a.setaid(rs.getInt("aid"));
				a.setaname(rs.getString("aname"));
				a.setPwd(rs.getString("pwd"));
				//讲对象存储到集合中
				allAdmin.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//关闭资源
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//返回结果
		return allAdmin;
	}
	
}
