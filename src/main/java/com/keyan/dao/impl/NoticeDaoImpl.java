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
	//��ȡ���п�����Ŀ��Ϣ
	@Override
	public List<Notice> ShownoticeDao() {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//����allKyProject����
		List<Notice> allNotice = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "select * from t_notice";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//ִ��sql
			rs = ps.executeQuery();
			//�����ϸ�ֵ
			allNotice = new ArrayList<Notice>();
			//�������
			while (rs.next()) {
				//��������ֵ
				Notice n = new Notice();
				n.setId(rs.getInt("id"));
				n.setNitile(rs.getString("nitile"));
				n.setNcontent(rs.getString("ncontent"));
				n.setNtime(rs.getString("ntime"));
				n.setNdepartid(rs.getString("ndepartid"));
				//������洢��������
				allNotice.add(n);
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
		System.out.println("���ݿ�鵽��" + allNotice);
		return allNotice;
	}

	//����¹���
	@Override
	public void addNotice(Notice notice) {
		//����jdbc����
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "insert into t_notice(nitile,ncontent,ntime,ndepartid) values(?,?,?,?)";
			//����sql�������
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getNitile());
			ps.setString(2, notice.getNcontent());
			ps.setString(3, notice.getNtime());
			ps.setString(4, notice.getNdepartid());
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
	public int deleteNoticeDao(int id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "delete from t_notice where id=?";
			//����SQL�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setInt(1, id);
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

	//ͨ��id��ѯ�޸Ŀ�����Ŀ
	@Override
	public Notice showModifyNoticeDao(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//����allKyProject����
		Notice oneNotice = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����
			String sql = "select * from t_notice where id=?";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setInt(1, id);
			//ִ��sql
			rs = ps.executeQuery();
			//�����ϸ�ֵ
			oneNotice = new Notice();
			//�������
			while (rs.next()) {
				//��������ֵ
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
		System.out.println("���ݿ�鵽��" + oneNotice);
		return oneNotice;
	}
	//����id�޸Ĺ�����Ϣ
	@Override
	public int ChangeNoticeDao(int id, String nitile, String ncontent) {
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
			String sql = "update t_notice set nitile=?,ncontent=? where id=?";
			//����SQL�������
			ps = conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setString(1, nitile);
			ps.setString(2, ncontent);
			ps.setInt(3, id);
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

	//�������湦��
	@Override
	public List<Notice> searchDao(String nitile) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//����searchNotice����
		List<Notice> searchNotice = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyan?useSSL=false", "root", "admin");
			//����sql����'%"+ptitle+"%'
			String sql = "select * from t_notice where nitile like '%"+nitile+"%'";
			//����sql�������
			ps = conn.prepareStatement(sql);
			//ִ��sql
			rs = ps.executeQuery();
			//�����ϸ�ֵ
			searchNotice = new ArrayList<Notice>();
			//�������
			while (rs.next()) {
				//��������ֵ
				Notice n = new Notice();
				n.setId(rs.getInt("id"));
				n.setNitile(rs.getString("nitile"));
				n.setNcontent(rs.getString("ncontent"));
				n.setNtime(rs.getString("ntime"));
				n.setNdepartid(rs.getString("ndepartid"));
				//������洢��������
				searchNotice.add(n);
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
		System.out.println("�����������ݿ����ӣ����ص������ݣ�" + searchNotice);
		return searchNotice;
	}

	

}
