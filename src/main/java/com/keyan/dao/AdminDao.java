package com.keyan.dao;

import java.util.List;

import com.keyan.pojo.Admin;

public interface AdminDao {
	/**
	 * �����û����������ѯ�û���Ϣ
	 * @param nname �û���
	 * @param pwd ����
	 * @return ���ز�ѯ�����û���Ϣ
	 */
	Admin checkAdminLoginDao(String nname,String pwd);

	/**
	 * �����û�id�޸��û�����
	 * @param anid
	 * @param newPwd
	 * @return
	 */
	int adminChangePwdDso(int anid, String newPwd);

	/**��ȡ�����û���Ϣ
	 * @return
	 */
	List<Admin> adminShowDao();
}
