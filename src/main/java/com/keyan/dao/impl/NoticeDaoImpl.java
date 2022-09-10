package com.keyan.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.keyan.dao.NoticeDao;
import com.keyan.pojo.KyProject;
import com.keyan.pojo.Notice;

public class NoticeDaoImpl implements NoticeDao{
	//获取所有科研项目信息
	@Override
	public List<Notice> ShownoticeDao() {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明allKyProject集合
		List<Notice> allNotice = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "select * from t_notice";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//执行sql
			rs = ps.executeQuery();
			//给集合赋值
			allNotice = new ArrayList<Notice>();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				Notice n = new Notice();
				n.setId(rs.getInt("id"));
				n.setNitile(rs.getString("nitile"));
				n.setNcontent(rs.getString("ncontent"));
				n.setNtime(rs.getString("ntime"));
				n.setNdepartid(rs.getString("ndepartid"));
				//讲对象存储到集合中
				allNotice.add(n);
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
		System.out.println("数据库查到的" + allNotice);
		return allNotice;
	}

	//添加新公告
	@Override
	public void addNotice(Notice notice) {
		//声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "insert into t_notice(nitile,ncontent,ntime,ndepartid) values(?,?,?,?)";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getNitile());
			ps.setString(2, notice.getNcontent());
			ps.setString(3, notice.getNtime());
			ps.setString(4, notice.getNdepartid());
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
	public int deleteNoticeDao(int id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "delete from t_notice where id=?";
			//创建SQL命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setInt(1, id);
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

	//通过id查询修改科研项目
	@Override
	public Notice showModifyNoticeDao(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//声明allKyProject集合
		Notice oneNotice = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令
			String sql = "select * from t_notice where id=?";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setInt(1, id);
			//执行sql
			rs = ps.executeQuery();
			//给集合赋值
			oneNotice = new Notice();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				oneNotice = new Notice();
				oneNotice.setId(rs.getInt("id"));
				oneNotice.setNitile(rs.getString("nitile"));
				oneNotice.setNcontent(rs.getString("ncontent"));
				oneNotice.setNtime(rs.getString("ntime"));
				oneNotice.setNdepartid(rs.getString("ndepartid"));
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
		System.out.println("数据库查到的" + oneNotice);
		return oneNotice;
	}
	//根据id修改公告信息
	@Override
	public int ChangeNoticeDao(int id, String nitile, String ncontent) {
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
			String sql = "update t_notice set nitile=?,ncontent=? where id=?";
			//创建SQL命令对象
			ps = conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1, nitile);
			ps.setString(2, ncontent);
			ps.setInt(3, id);
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

	//搜索公告功能
	@Override
	public List<Notice> searchDao(String nitile) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//声明searchNotice集合
		List<Notice> searchNotice = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//创建sql命令'%"+ptitle+"%'
			String sql = "select * from t_notice where nitile like '%"+nitile+"%'";
			//创建sql命令对象
			ps = conn.prepareStatement(sql);
			//执行sql
			rs = ps.executeQuery();
			//给集合赋值
			searchNotice = new ArrayList<Notice>();
			//遍历结果
			while (rs.next()) {
				//给变量赋值
				Notice n = new Notice();
				n.setId(rs.getInt("id"));
				n.setNitile(rs.getString("nitile"));
				n.setNcontent(rs.getString("ncontent"));
				n.setNtime(rs.getString("ntime"));
				n.setNdepartid(rs.getString("ndepartid"));
				//讲对象存储到集合中
				searchNotice.add(n);
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
		System.out.println("我来到了数据库链接，搜素到的内容：" + searchNotice);
		return searchNotice;
	}

	

}
