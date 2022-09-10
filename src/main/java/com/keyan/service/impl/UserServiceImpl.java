package com.keyan.service.impl;

import java.util.List;

import com.keyan.dao.UserDao;
import com.keyan.dao.impl.UserDaoImpl;
import com.keyan.pojo.Notice;
import com.keyan.pojo.User;
import com.keyan.service.UserService;

public class UserServiceImpl implements UserService{
	//����Dao�����
	UserDao ud =new UserDaoImpl();
	//�û���¼
	@Override
	public User checkUserLoginService(String uname, String pwd) {
		
		return ud.checkUserLoginDao(uname, pwd);
	}
	//�޸��û�����
	@Override
	public int userChangePwdService(int unid, String newPwd) {
		
		return ud.userChangePwdDso(unid,newPwd);
	}
	//��ȡ�����û���Ϣ
	@Override
	public List<User> userShowService() {
		
		return ud.userShowDao();
	}
	//��������˺Ź���
	@Override
	public void addUser(User addu) {
		
		ud.addUserDao(addu);
		
	}
	
	//����ɾ���˺Ź���
	@Override
	public int deleteUser(int uid) {
		ud.deleteUserDao(uid);
		return 0;
	}
	
	//����uid��ѯ���޸��˺���Ϣ
	@Override
	public User showMmodifyUserService(int uid) {
		
		return ud.showMmodifyUserDao(uid);
	}
	
	//����Ա����uid�޸Ľ�ʦ�˺���Ϣ
	@Override
	public int modifyuserpwdService(int uid, String modifyuser, String modifypwd) {
		
		return ud.modifyuserpwdDao(uid,modifyuser,modifypwd);
	}
	
	//����aid�޸Ĺ���Ա��������
	@Override
	public int adminChangePwdService(int aid, String newadminPwd) {
		
		return ud.modifyadminpwdDao(aid,newadminPwd);
	}

}
