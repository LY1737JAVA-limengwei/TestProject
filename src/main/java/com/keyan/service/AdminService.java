package com.keyan.service;

import java.util.List;

import com.keyan.pojo.KyProject;
import com.keyan.pojo.Admin;


public interface AdminService {
	/**��ݼ� alt+shift+j
	 * 
	 * У���û���¼ 
	 * @param aname �û���
	 * @param pwd ����
	 * @return ���ز�ѯ�����û���Ϣ
	 */
	Admin checkAdminLoginService(String aname,String pwd);
	
	/**
	 * �޸��û�����
	 * @param anid
	 * @param newPwd
	 * @return
	 */
	int adminChangePwdService(int anid,String newPwd);

	/**
	 * ��ȡ�����û���Ϣ
	 * @return
	 */
	List<Admin> adminShowService();

}
