package com.keyan.service;

import java.util.List;

import com.keyan.pojo.KyProject;
import com.keyan.pojo.Notice;
import com.keyan.pojo.User;


public interface UserService {
	/**��ݼ� alt+shift+j
	 * 
	 * У���û���¼ 
	 * @param uname �û���
	 * @param pwd ����
	 * @return ���ز�ѯ�����û���Ϣ
	 */
	User checkUserLoginService(String uname,String pwd);
	
	/**
	 * �޸��û�����
	 * @param unid
	 * @param newPwd
	 * @return
	 */
	int userChangePwdService(int unid,String newPwd);

	/**
	 * ��ȡ�����û���Ϣ
	 * @return
	 */
	List<User> userShowService();
	
	/**
	 * ����˺Ź���
	 * @param addu
	 */
	void addUser(User addu);

	/**
	 * ����ɾ���˺Ź���
	 * @param uid
	 * @return
	 */
	int deleteUser(int uid);
	
	/**
	 * ����uid��ѯ���޸��˺���Ϣ
	 * @param uid
	 * @return
	 */
	User showMmodifyUserService(int uid);

	/**
	 * ����Ա����uid�޸Ľ�ʦ�˺���Ϣ
	 * @param uid
	 * @param modifyuser
	 * @param modifypwd
	 * @return
	 */
	int modifyuserpwdService(int uid, String modifyuser, String modifypwd);

	//����aid�޸Ĺ���Ա��������
	int adminChangePwdService(int aid, String newadminPwd);

}
