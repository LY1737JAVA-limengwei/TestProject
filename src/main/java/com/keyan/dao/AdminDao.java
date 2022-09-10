package com.keyan.dao;

import java.util.List;

import com.keyan.pojo.Admin;

public interface AdminDao {
	/**
	 * 根据用户名和密码查询用户信息
	 * @param nname 用户名
	 * @param pwd 密码
	 * @return 返回查询到的用户信息
	 */
	Admin checkAdminLoginDao(String nname,String pwd);

	/**
	 * 根据用户id修改用户密码
	 * @param anid
	 * @param newPwd
	 * @return
	 */
	int adminChangePwdDso(int anid, String newPwd);

	/**获取所有用户信息
	 * @return
	 */
	List<Admin> adminShowDao();
}
