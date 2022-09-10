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
	//�����û����������ѯ�û���Ϣ
	@Override
	public Admin checkAdminLoginDao(String aname, String pwd) {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//��������
		Admin a = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");//mysql-conncctor-java-8.0
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan", "root", "admin");//mysql-conncctor-java-5.1
			//����sql����
			String sql = "select * from t_admin where aname=? and pwd=?";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setString(1, aname);
			ps.setString(2, pwd);
			//ִ��sql
			rs = ps.executeQuery();
			//�������
			while (rs.next()) {
				//��������ֵ
				a = new Admin();
				a.setaid(rs.getInt("aid"));
				a.setaname(rs.getString("aname"));
				a.setPwd(rs.getString("pwd"));
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
		System.out.println("���Թ���Ա���ѯ���" + a);
		return a;
	}

	//���ݹ���Աid�޸Ĺ���Ա����
	@Override
	public int adminChangePwdDso(int anid, String newPwd) {
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
			String sql = "update t_admin set pwd=? where uid=?";
			//����SQL�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setString(1, newPwd);
			ps.setInt(2, anid);
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
	public List<Admin> adminShowDao() {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//����allUser����
		List<Admin> allAdmin = null;
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
			allAdmin = new ArrayList<Admin>();
			//�������
			while (rs.next()) {
				//��������ֵ
				Admin a = new Admin();
				a.setaid(rs.getInt("aid"));
				a.setaname(rs.getString("aname"));
				a.setPwd(rs.getString("pwd"));
				//������洢��������
				allAdmin.add(a);
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
		return allAdmin;
	}
	
}
