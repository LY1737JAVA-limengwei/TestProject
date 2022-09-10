package com.keyan.dao;

import java.util.List;

import com.keyan.pojo.User;

public interface UserDao {
	/**
	 * 根据用户名和密码查询用户信息
	 * @param uname 用户名
	 * @param pwd 密码
	 * @return 返回查询到的用户信息
	 */
	User checkUserLoginDao(String uname,String pwd);

	/**
	 * 根据用户id修改用户密码
	 * @param unid
	 * @param newPwd
	 * @return
	 */
	int userChangePwdDso(int unid, String newPwd);

	/**获取所有用户信息
	 * @return
	 */
	List<User> userShowDao();

	/**
	 * 添加账号功能
	 * @param addu
	 */
	void addUserDao(User addu);

	/**
	 * 调用删除账号功能
	 * @param uid
	 */
	int deleteUserDao(int uid);

	/**
	 * 根据uid查询被修改账号信息
	 * @param uid
	 * @return
	 */
	User showMmodifyUserDao(int uid);

	/**
	 * 管理员根据uid修改教师账号信息
	 * @param uid
	 * @param modifyuser
	 * @param modifypwd
	 * @return
	 */
	int modifyuserpwdDao(int uid, String modifyuser, String modifypwd);

	/**
	 * 根据aid修改管理员本人密码
	 * @param aid
	 * @param newadminPwd
	 * @return
	 */
	int modifyadminpwdDao(int aid, String newadminPwd);
}
