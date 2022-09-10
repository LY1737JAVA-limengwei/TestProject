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
	//��ȡ���п�����Ŀ��Ϣ
	@Override
	public List<KyProject> projectShowService() {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//����allKyProject����
		List<KyProject> allKyProject = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "select * from t_project";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//ִ��sql
			rs = ps.executeQuery();
			//�����ϸ�ֵ
			allKyProject = new ArrayList<KyProject>();
			//�������
			while (rs.next()) {
				//��������ֵ
				KyProject k = new KyProject();
				k.setPid(rs.getInt("pid"));
				k.setApname(rs.getString("apname"));
				k.setExname(rs.getString("exname"));
				k.setPstate(rs.getInt("pstate"));
				k.setPtitle(rs.getString("ptitle"));
				k.setPtype(rs.getString("ptype"));
				k.setPtime(rs.getString("ptime"));
				//������洢��������
				allKyProject.add(k);
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
		return allKyProject;
	}
	

	//ajax��ȡ���п�����Ŀ��Ϣ
	@Override
	public List<Map<String, Object>> ajaxprojectShowService() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "select * from t_project";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//ִ��sql
			rs = ps.executeQuery();
			
			
			//����allKyProject����
			List<KyProject> allKyProject1 = null;
			//�����ϸ�ֵ
			allKyProject1 = new ArrayList<KyProject>();
			//�������
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("pid", rs.getInt("pid"));
				map.put("ptitle", rs.getString("ptitle"));
				map.put("apname", rs.getString("apname"));
				map.put("ptype", rs.getString("ptype"));
				map.put("ptime", rs.getString("ptime"));
				map.put("pstate", rs.getInt("pstate"));
				map.put("exname", rs.getString("exname"));
				//������洢��list������
				list.add(map); 
				
			}
			
			/*
			 * //������� while (rs.next()) { HashMap<String, Object> map = new HashMap<String,
			 * Object>(); map.put("pid", rs.getInt("pid")); map.put("apname",
			 * rs.getString("apname")); map.put("exname", rs.getString("exname"));
			 * map.put("pstate", rs.getInt("pstate")); map.put("ptitle",
			 * rs.getString("ptitle")); map.put("ptype", rs.getString("ptype"));
			 * map.put("ptime", rs.getString("ptime")); //������洢��list������ list.add(map);
			 * System.out.println("ajax�����ݿ�鵽��map���ݣ�" + map); }
			 */
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
		
		System.out.println("ajax�����ݿ�鵽��list���ݣ�" + list);
		//���ؽ��
		return list;
	}
	
	//������Ŀ����
	@Override
	public List<KyProject> searchProjectService(String ptitle) {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		/*��ĳ�ַ�����ָ��λ��׷���ַ�
		 * StringBuffer add = new StringBuffer(); add.append(sptitle).insert(0, "%");
		 * add.append("%"); String ptitle1 = add.toString();
		 * System.out.println(ptitle1);
		 */
		
		//����allKyProject����
		List<KyProject> searchKyProject = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����'%"+ptitle+"%'
			String sql = "select * from t_project where ptitle like '%"+ptitle+"%'";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//ִ��sql
			rs = ps.executeQuery();
			//�����ϸ�ֵ
			searchKyProject = new ArrayList<KyProject>();
			//�������
			while (rs.next()) {
				//��������ֵ
				KyProject k = new KyProject();
				k.setPid(rs.getInt("pid"));
				k.setApname(rs.getString("apname"));
				k.setExname(rs.getString("exname"));
				k.setPstate(rs.getInt("pstate"));
				k.setPtitle(rs.getString("ptitle"));
				k.setPtype(rs.getString("ptype"));
				k.setPtime(rs.getString("ptime"));
				//������洢��������
				searchKyProject.add(k);
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
		System.out.println("�����������ݿ����ӣ����ص������ݣ�" + searchKyProject);
		return searchKyProject;
	}
	
	//��ʾ����������Ŀ
	@Override
	public List<KyProject> pendingProjectService() {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//����allKyProject����
		List<KyProject> pendingKyProject = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "select * from t_project where pstate = 1";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//ִ��sql
			rs = ps.executeQuery();
			//�����ϸ�ֵ
			pendingKyProject = new ArrayList<KyProject>();
			//�������
			while (rs.next()) {
				//��������ֵ
				KyProject k = new KyProject();
				k.setPid(rs.getInt("pid"));
				k.setApname(rs.getString("apname"));
				k.setExname(rs.getString("exname"));
				k.setPstate(rs.getInt("pstate"));
				k.setPtitle(rs.getString("ptitle"));
				k.setPtype(rs.getString("ptype"));
				k.setPtime(rs.getString("ptime"));
				//������洢��������
				pendingKyProject.add(k);
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
		return pendingKyProject;
	}
	
	//������Ŀ
	@Override
	public int approvaProject(int pid,int pstate) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "update t_project set pstate = ? where pid = ?";
			//����SQL�������
			ps = conn.prepareStatement(sql);
			
			//��ռλ����ֵ
			System.out.println("���ݿ�����λ��pstateֵ��" + pstate + "pidֵ��" + pid);
			ps.setInt(1, pstate);
			ps.setInt(2, pid);
			
			
			
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

	//�����Ŀ
	@Override
	public void addProject(KyProject kyProject) {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "insert into t_project(apname,exname,pstate,ptitle,ptype,ptime) values(?,?,?,?,?,?)";
			//����sql�������
			ps = conn.prepareStatement(sql);
			ps.setString(1, kyProject.getApname());
			ps.setString(2, kyProject.getExname());
			ps.setInt(3, kyProject.getPstate());
			ps.setString(4, kyProject.getPtitle());
			ps.setString(5, kyProject.getPtype());
			ps.setString(6, kyProject.getPtime());
			
			
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

	//ɾ��һ����Ŀ
	@Override
	public int deleteProject(int pid) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "delete from t_project where pid=?";
			//����SQL�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setInt(1, pid);
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

	//ͨ��pid��ѯ��Ҫ�޸ĵĿ�����Ŀ
	@Override
	public KyProject showmodifyProjectDao(int pid) {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		KyProject k = null;
		//����allKyProject����
		List<KyProject> allKyProject = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "select * from t_project where pid=?";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setInt(1, pid);
			//ִ��sql
			rs = ps.executeQuery();
			//�������
			while (rs.next()) {
				//��������ֵ
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
		System.out.println("��ѯ����Ҫ�޸ĵ���Ŀ��Ϣ" + k);
		return k;
	}

	//ͨ��pid�޸Ŀ�����Ŀ
	@Override
	public int ChangeKyProjectDao(int pid, String ptitle, String ptype) {
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
			String sql = "update t_project set ptitle=?,ptype=? where pid=?";
			//����SQL�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setString(1, ptitle);
			ps.setString(2, ptype);
			ps.setInt(3, pid);
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
