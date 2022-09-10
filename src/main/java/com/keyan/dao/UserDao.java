package com.keyan.dao;

import java.util.List;

import com.keyan.pojo.User;

public interface UserDao {
	/**
	 * �����û����������ѯ�û���Ϣ
	 * @param uname �û���
	 * @param pwd ����
	 * @return ���ز�ѯ�����û���Ϣ
	 */
	User checkUserLoginDao(String uname,String pwd);

	/**
	 * �����û�id�޸��û�����
	 * @param unid
	 * @param newPwd
	 * @return
	 */
	int userChangePwdDso(int unid, String newPwd);

	/**��ȡ�����û���Ϣ
	 * @return
	 */
	List<User> userShowDao();

	/**
	 * ����˺Ź���
	 * @param addu
	 */
	void addUserDao(User addu);

	/**
	 * ����ɾ���˺Ź���
	 * @param uid
	 */
	int deleteUserDao(int uid);

	/**
	 * ����uid��ѯ���޸��˺���Ϣ
	 * @param uid
	 * @return
	 */
	User showMmodifyUserDao(int uid);

	/**
	 * ����Ա����uid�޸Ľ�ʦ�˺���Ϣ
	 * @param uid
	 * @param modifyuser
	 * @param modifypwd
	 * @return
	 */
	int modifyuserpwdDao(int uid, String modifyuser, String modifypwd);

	/**
	 * ����aid�޸Ĺ���Ա��������
	 * @param aid
	 * @param newadminPwd
	 * @return
	 */
	int modifyadminpwdDao(int aid, String newadminPwd);
}
