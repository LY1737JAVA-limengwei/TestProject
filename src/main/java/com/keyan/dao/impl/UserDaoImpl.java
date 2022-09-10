package com.keyan.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.keyan.dao.UserDao;
import com.keyan.pojo.Notice;
import com.keyan.pojo.User;

public class UserDaoImpl implements UserDao{
	//根据用户名和密码查询用户信息
	@Override
	public User checkUserLoginDao(String uname, String pwd) {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明变量
		User u = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");//mysql-conncctor-java-8.0
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan", "root", "admin");//mysql-conncctor-java-5.1
			//创建sql命令
			String sql = "select * from t_user where uname=? and pwd=?";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, uname);
			ps.setString(2, pwd);
			//执行sql
			rs = ps.executeQuery();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
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
		return u;
	}

	//根据用户id修改用户密码
	@Override
	public int userChangePwdDso(int unid, String newPwd) {
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
			String sql = "update t_user set pwd=? where uid=?";
			//创建SQL命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, newPwd);
			ps.setInt(2, unid);
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
	
	//根据用户id修改管理员本人密码
		@Override
		public int modifyadminpwdDao(int aid, String newadminPwd) {
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
				String sql = "update t_admin set pwd=? where aid=?";
				//创建SQL命令对象
				ps = conn.prepareStatement(sql);
				//给占位符赋值
				ps.setString(1, newadminPwd);
				ps.setInt(2,aid);
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
	public List<User> userShowDao() {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明allUser集合
		List<User> allUser = null;
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
			allUser = new ArrayList<User>();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				//讲对象存储到集合中
				allUser.add(u);
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
		return allUser;
	}

	//添加账号功能
	@Override
	public void addUserDao(User addu) {
		// 声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "insert into t_user(uname,pwd) values(?,?)";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, addu.getUname());
			ps.setString(2, addu.getPwd());
			//执行sql
			ps.execute();
			//关闭资源
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	//调用删除账号功能
	@Override
	public int deleteUserDao(int uid) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "delete from t_user where uid=?";
			//创建SQL命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setInt(1, uid);
			//执行sql，并返回受影响的行数
			result = ps.executeUpdate();
			//关闭资源
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	//根据uid查询被修改账号信息
	@Override
	public User showMmodifyUserDao(int uid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明showMmodifyUser集合
		User showMmodifyUser = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "select * from t_user where uid=?";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setInt(1, uid);
			//执行sql
			rs = ps.executeQuery();
			//给集合赋值
			showMmodifyUser = new User();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				showMmodifyUser.setUid(rs.getInt("uid"));
				showMmodifyUser.setUname(rs.getString("uname"));
				showMmodifyUser.setPwd(rs.getString("pwd"));
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
		System.out.println("数据库查到的" + showMmodifyUser);
		return showMmodifyUser;
	}

	//管理员根据uid修改教师账号信息
	@Override
	public int modifyuserpwdDao(int uid, String modifyuser, String modifypwd) {
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
			String sql = "update t_user set uname=?,pwd=? where uid=?";
			//创建SQL命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, modifyuser);
			ps.setString(2, modifypwd);
			ps.setInt(3, uid);
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
	
}
