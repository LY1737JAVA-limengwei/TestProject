package com.keyan.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.keyan.dao.KyProjectDao;
import com.keyan.pojo.KyProject;
import com.keyan.pojo.User;

public class KyProjectDaoImpl implements KyProjectDao{
	//获取所有科研项目信息
	@Override
	public List<KyProject> projectShowService() {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明allKyProject集合
		List<KyProject> allKyProject = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "select * from t_project";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//执行sql
			rs = ps.executeQuery();
			//给集合赋值
			allKyProject = new ArrayList<KyProject>();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				KyProject k = new KyProject();
				k.setPid(rs.getInt("pid"));
				k.setApname(rs.getString("apname"));
				k.setExname(rs.getString("exname"));
				k.setPstate(rs.getInt("pstate"));
				k.setPtitle(rs.getString("ptitle"));
				k.setPtype(rs.getString("ptype"));
				k.setPtime(rs.getString("ptime"));
				//讲对象存储到集合中
				allKyProject.add(k);
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
		return allKyProject;
	}
	

	//ajax获取所有科研项目信息
	@Override
	public List<Map<String, Object>> ajaxprojectShowService() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "select * from t_project";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//执行sql
			rs = ps.executeQuery();
			
			
			//声明allKyProject集合
			List<KyProject> allKyProject1 = null;
			//给集合赋值
			allKyProject1 = new ArrayList<KyProject>();
			//遍历结果
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("pid", rs.getInt("pid"));
				map.put("ptitle", rs.getString("ptitle"));
				map.put("apname", rs.getString("apname"));
				map.put("ptype", rs.getString("ptype"));
				map.put("ptime", rs.getString("ptime"));
				map.put("pstate", rs.getInt("pstate"));
				map.put("exname", rs.getString("exname"));
				//讲对象存储到list集合中
				list.add(map); 
				
			}
			
			/*
			 * //遍历结果 while (rs.next()) { HashMap<String, Object> map = new HashMap<String,
			 * Object>(); map.put("pid", rs.getInt("pid")); map.put("apname",
			 * rs.getString("apname")); map.put("exname", rs.getString("exname"));
			 * map.put("pstate", rs.getInt("pstate")); map.put("ptitle",
			 * rs.getString("ptitle")); map.put("ptype", rs.getString("ptype"));
			 * map.put("ptime", rs.getString("ptime")); //讲对象存储到list集合中 list.add(map);
			 * System.out.println("ajax从数据库查到的map数据：" + map); }
			 */
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
		
		System.out.println("ajax从数据库查到的list数据：" + list);
		//返回结果
		return list;
	}
	
	//搜素项目功能
	@Override
	public List<KyProject> searchProjectService(String ptitle) {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		/*给某字符串在指定位置追加字符
		 * StringBuffer add = new StringBuffer(); add.append(sptitle).insert(0, "%");
		 * add.append("%"); String ptitle1 = add.toString();
		 * System.out.println(ptitle1);
		 */
		
		//声明allKyProject集合
		List<KyProject> searchKyProject = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令'%"+ptitle+"%'
			String sql = "select * from t_project where ptitle like '%"+ptitle+"%'";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//执行sql
			rs = ps.executeQuery();
			//给集合赋值
			searchKyProject = new ArrayList<KyProject>();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				KyProject k = new KyProject();
				k.setPid(rs.getInt("pid"));
				k.setApname(rs.getString("apname"));
				k.setExname(rs.getString("exname"));
				k.setPstate(rs.getInt("pstate"));
				k.setPtitle(rs.getString("ptitle"));
				k.setPtype(rs.getString("ptype"));
				k.setPtime(rs.getString("ptime"));
				//讲对象存储到集合中
				searchKyProject.add(k);
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
		System.out.println("我来到了数据库链接，搜素到的内容：" + searchKyProject);
		return searchKyProject;
	}
	
	//显示待审批的项目
	@Override
	public List<KyProject> pendingProjectService() {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明allKyProject集合
		List<KyProject> pendingKyProject = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "select * from t_project where pstate = 1";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//执行sql
			rs = ps.executeQuery();
			//给集合赋值
			pendingKyProject = new ArrayList<KyProject>();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				KyProject k = new KyProject();
				k.setPid(rs.getInt("pid"));
				k.setApname(rs.getString("apname"));
				k.setExname(rs.getString("exname"));
				k.setPstate(rs.getInt("pstate"));
				k.setPtitle(rs.getString("ptitle"));
				k.setPtype(rs.getString("ptype"));
				k.setPtime(rs.getString("ptime"));
				//讲对象存储到集合中
				pendingKyProject.add(k);
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
		return pendingKyProject;
	}
	
	//审批项目
	@Override
	public int approvaProject(int pid,int pstate) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "update t_project set pstate = ? where pid = ?";
			//创建SQL命令对象
			ps = conn.prepareStatement(sql);
			
			//给占位符赋值
			System.out.println("数据库链接位置pstate值：" + pstate + "pid值：" + pid);
			ps.setInt(1, pstate);
			ps.setInt(2, pid);
			
			
			
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

	//添加项目
	@Override
	public void addProject(KyProject kyProject) {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "insert into t_project(apname,exname,pstate,ptitle,ptype,ptime) values(?,?,?,?,?,?)";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, kyProject.getApname());
			ps.setString(2, kyProject.getExname());
			ps.setInt(3, kyProject.getPstate());
			ps.setString(4, kyProject.getPtitle());
			ps.setString(5, kyProject.getPtype());
			ps.setString(6, kyProject.getPtime());
			
			
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

	//删除一个项目
	@Override
	public int deleteProject(int pid) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "delete from t_project where pid=?";
			//创建SQL命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setInt(1, pid);
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

	//通过pid查询需要修改的科研项目
	@Override
	public KyProject showmodifyProjectDao(int pid) {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		KyProject k = null;
		//声明allKyProject集合
		List<KyProject> allKyProject = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "select * from t_project where pid=?";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setInt(1, pid);
			//执行sql
			rs = ps.executeQuery();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				k = new KyProject();
				k.setPid(rs.getInt("pid"));
				k.setApname(rs.getString("apname"));
				k.setExname(rs.getString("exname"));
				k.setPstate(rs.getInt("pstate"));
				k.setPtitle(rs.getString("ptitle"));
				k.setPtype(rs.getString("ptype"));
				k.setPtime(rs.getString("ptime"));
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
		System.out.println("查询到需要修改的项目信息" + k);
		return k;
	}

	//通过pid修改科研项目
	@Override
	public int ChangeKyProjectDao(int pid, String ptitle, String ptype) {
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
			String sql = "update t_project set ptitle=?,ptype=? where pid=?";
			//创建SQL命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, ptitle);
			ps.setString(2, ptype);
			ps.setInt(3, pid);
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
