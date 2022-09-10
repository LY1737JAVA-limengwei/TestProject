package com.keyan.service;

import java.util.List;

import com.keyan.pojo.KyProject;
import com.keyan.pojo.Notice;
import com.keyan.pojo.User;


public interface UserService {
	/**快捷键 alt+shift+j
	 * 
	 * 校验用户登录 
	 * @param uname 用户名
	 * @param pwd 密码
	 * @return 返回查询到的用户信息
	 */
	User checkUserLoginService(String uname,String pwd);
	
	/**
	 * 修改用户密码
	 * @param unid
	 * @param newPwd
	 * @return
	 */
	int userChangePwdService(int unid,String newPwd);

	/**
	 * 获取所有用户信息
	 * @return
	 */
	List<User> userShowService();
	
	/**
	 * 添加账号功能
	 * @param addu
	 */
	void addUser(User addu);

	/**
	 * 调用删除账号功能
	 * @param uid
	 * @return
	 */
	int deleteUser(int uid);
	
	/**
	 * 根据uid查询被修改账号信息
	 * @param uid
	 * @return
	 */
	User showMmodifyUserService(int uid);

	/**
	 * 管理员根据uid修改教师账号信息
	 * @param uid
	 * @param modifyuser
	 * @param modifypwd
	 * @return
	 */
	int modifyuserpwdService(int uid, String modifyuser, String modifypwd);

	//根据aid修改管理员本人密码
	int adminChangePwdService(int aid, String newadminPwd);

}
