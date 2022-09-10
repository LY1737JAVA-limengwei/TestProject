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
	//�����û����������ѯ�û���Ϣ
	@Override
	public User checkUserLoginDao(String uname, String pwd) {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//��������
		User u = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");//mysql-conncctor-java-8.0
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan", "root", "admin");//mysql-conncctor-java-5.1
			//����sql����
			String sql = "select * from t_user where uname=? and pwd=?";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setString(1, uname);
			ps.setString(2, pwd);
			//ִ��sql
			rs = ps.executeQuery();
			//�������
			while (rs.next()) {
				//��������ֵ
				u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ر���Դ
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
		//���ؽ��
		return u;
	}

	//�����û�id�޸��û�����
	@Override
	public int userChangePwdDso(int unid, String newPwd) {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		//��������
		int index = -1;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����SQL����
			String sql = "update t_user set pwd=? where uid=?";
			//����SQL�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setString(1, newPwd);
			ps.setInt(2, unid);
			//ִ��
			index = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("�������ݿ��õ���" + index);
		}finally {
			//�ر���Դ
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
		//���ؽ��
		return index;
	}
	
	//�����û�id�޸Ĺ���Ա��������
		@Override
		public int modifyadminpwdDao(int aid, String newadminPwd) {
			//����jdbc����
			Connection conn = null;
			PreparedStatement ps = null;
			//��������
			int index = -1;
			try {
				//��������
				Class.forName("com.mysql.jdbc.Driver");
				//��ȡ����
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
				//����SQL����
				String sql = "update t_admin set pwd=? where aid=?";
				//����SQL�������
				ps = conn.prepareStatement(sql);
				//��ռλ����ֵ
				ps.setString(1, newadminPwd);
				ps.setInt(2,aid);
				//ִ��
				index = ps.executeUpdate();
			} catch (Exception e) {
				System.out.println("�������ݿ��õ���" + index);
			}finally {
				//�ر���Դ
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
			//���ؽ��
			return index;
		}

	//��ȡ�����û���Ϣ
	@Override
	public List<User> userShowDao() {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//����allUser����
		List<User> allUser = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");//mysql-conncctor-java-8.0
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan", "root", "admin");//mysql-conncctor-java-5.1
			//����sql����
			String sql = "select * from t_user";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//ִ��sql
			rs = ps.executeQuery();
			//�����ϸ�ֵ
			allUser = new ArrayList<User>();
			//�������
			while (rs.next()) {
				//��������ֵ
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				//������洢��������
				allUser.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ر���Դ
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
		//���ؽ��
		return allUser;
	}

	//����˺Ź���
	@Override
	public void addUserDao(User addu) {
		// ����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "insert into t_user(uname,pwd) values(?,?)";
			//����sql�������
			ps = conn.prepareStatement(sql);
			ps.setString(1, addu.getUname());
			ps.setString(2, addu.getPwd());
			//ִ��sql
			ps.execute();
			//�ر���Դ
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	//����ɾ���˺Ź���
	@Override
	public int deleteUserDao(int uid) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "delete from t_user where uid=?";
			//����SQL�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setInt(1, uid);
			//ִ��sql����������Ӱ�������
			result = ps.executeUpdate();
			//�ر���Դ
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	//����uid��ѯ���޸��˺���Ϣ
	@Override
	public User showMmodifyUserDao(int uid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//����showMmodifyUser����
		User showMmodifyUser = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "select * from t_user where uid=?";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setInt(1, uid);
			//ִ��sql
			rs = ps.executeQuery();
			//�����ϸ�ֵ
			showMmodifyUser = new User();
			//�������
			while (rs.next()) {
				//��������ֵ
				showMmodifyUser.setUid(rs.getInt("uid"));
				showMmodifyUser.setUname(rs.getString("uname"));
				showMmodifyUser.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ر���Դ
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
		//���ؽ��
		System.out.println("���ݿ�鵽��" + showMmodifyUser);
		return showMmodifyUser;
	}

	//����Ա����uid�޸Ľ�ʦ�˺���Ϣ
	@Override
	public int modifyuserpwdDao(int uid, String modifyuser, String modifypwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		//��������
		int index = -1;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����SQL����
			String sql = "update t_user set uname=?,pwd=? where uid=?";
			//����SQL�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setString(1, modifyuser);
			ps.setString(2, modifypwd);
			ps.setInt(3, uid);
			//ִ��
			index = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("�������ݿ��õ���" + index);
		}finally {
			//�ر���Դ
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
		//���ؽ��
		return index;
	}
	
}
