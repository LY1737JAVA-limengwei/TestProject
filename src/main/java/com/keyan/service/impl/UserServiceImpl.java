package com.keyan.service.impl;

import java.util.List;

import com.keyan.dao.UserDao;
import com.keyan.dao.impl.UserDaoImpl;
import com.keyan.pojo.Notice;
import com.keyan.pojo.User;
import com.keyan.service.UserService;

public class UserServiceImpl implements UserService{
	//声明Dao层对象
	UserDao ud =new UserDaoImpl();
	//用户登录
	@Override
	public User checkUserLoginService(String uname, String pwd) {
		
		return ud.checkUserLoginDao(uname, pwd);
	}
	//修改用户密码
	@Override
	public int userChangePwdService(int unid, String newPwd) {
		
		return ud.userChangePwdDso(unid,newPwd);
	}
	//获取所有用户信息
	@Override
	public List<User> userShowService() {
		
		return ud.userShowDao();
	}
	//调用添加账号功能
	@Override
	public void addUser(User addu) {
		
		ud.addUserDao(addu);
		
	}
	
	//调用删除账号功能
	@Override
	public int deleteUser(int uid) {
		ud.deleteUserDao(uid);
		return 0;
	}
	
	//根据uid查询被修改账号信息
	@Override
	public User showMmodifyUserService(int uid) {
		
		return ud.showMmodifyUserDao(uid);
	}
	
	//管理员根据uid修改教师账号信息
	@Override
	public int modifyuserpwdService(int uid, String modifyuser, String modifypwd) {
		
		return ud.modifyuserpwdDao(uid,modifyuser,modifypwd);
	}
	
	//根据aid修改管理员本人密码
	@Override
	public int adminChangePwdService(int aid, String newadminPwd) {
		
		return ud.modifyadminpwdDao(aid,newadminPwd);
	}

}
