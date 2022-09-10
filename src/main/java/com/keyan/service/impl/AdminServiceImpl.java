package com.keyan.service.impl;

import java.util.List;

import com.keyan.dao.AdminDao;
import com.keyan.dao.impl.AdminDaoImpl;
import com.keyan.pojo.Admin;
import com.keyan.service.AdminService;

public class AdminServiceImpl implements AdminService{
	//声明Dao层对象
	AdminDao ad =new AdminDaoImpl();
	//管理员登录
	@Override
	public Admin checkAdminLoginService(String aname, String pwd) {
			
		return ad.checkAdminLoginDao(aname, pwd);
	}
	//修改用户密码
	@Override
	public int adminChangePwdService(int anid, String newPwd) {
		
		return ad.adminChangePwdDso(anid,newPwd);
	}
	//获取所有用户信息
	@Override
	public List<Admin> adminShowService() {
		
		return ad.adminShowDao();
	}



}
