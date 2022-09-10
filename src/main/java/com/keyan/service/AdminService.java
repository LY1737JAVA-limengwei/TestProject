package com.keyan.service;

import java.util.List;

import com.keyan.pojo.KyProject;
import com.keyan.pojo.Admin;


public interface AdminService {
	/**快捷键 alt+shift+j
	 * 
	 * 校验用户登录 
	 * @param aname 用户名
	 * @param pwd 密码
	 * @return 返回查询到的用户信息
	 */
	Admin checkAdminLoginService(String aname,String pwd);
	
	/**
	 * 修改用户密码
	 * @param anid
	 * @param newPwd
	 * @return
	 */
	int adminChangePwdService(int anid,String newPwd);

	/**
	 * 获取所有用户信息
	 * @return
	 */
	List<Admin> adminShowService();

}
