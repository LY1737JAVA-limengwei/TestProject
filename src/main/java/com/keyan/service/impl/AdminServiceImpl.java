package com.keyan.service.impl;

import java.util.List;

import com.keyan.dao.AdminDao;
import com.keyan.dao.impl.AdminDaoImpl;
import com.keyan.pojo.Admin;
import com.keyan.service.AdminService;

public class AdminServiceImpl implements AdminService{
	//����Dao�����
	AdminDao ad =new AdminDaoImpl();
	//����Ա��¼
	@Override
	public Admin checkAdminLoginService(String aname, String pwd) {
			
		return ad.checkAdminLoginDao(aname, pwd);
	}
	//�޸��û�����
	@Override
	public int adminChangePwdService(int anid, String newPwd) {
		
		return ad.adminChangePwdDso(anid,newPwd);
	}
	//��ȡ�����û���Ϣ
	@Override
	public List<Admin> adminShowService() {
		
		return ad.adminShowDao();
	}



}
