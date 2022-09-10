package com.keyan.service.impl;

import java.util.List;
import java.util.Map;

import com.keyan.dao.KyProjectDao;
import com.keyan.dao.impl.KyProjectDaoImpl;
import com.keyan.pojo.KyProject;
import com.keyan.service.KyProjectService;

public class KyProjectServiceImpl implements KyProjectService{
	//声明Dao层对象
	KyProjectDao kd = new KyProjectDaoImpl();
	
	//获取所有科研项目信息
	@Override
	public List<KyProject> projectShowService() {
		
		return kd.projectShowService();
	}
	
	//ajax获取所有科研项目信息
	@Override
	public List<Map<String, Object>> ajaxprojectShowService() {
		
		return kd.ajaxprojectShowService();
	}
	
	//显示待审批科研项目信息
	@Override
	public List<KyProject> pendingProjectService() {
			
		return kd.pendingProjectService();
	}
	
	//搜素项目功能
	@Override
	public List<KyProject> searchService(String ptitle) {

		return kd.searchProjectService(ptitle);
	}
	
	//审批项目
	@Override
	public int approvaProject(int pid,int pstate) {
		
		return kd.approvaProject(pid,pstate);
	}

	//添加项目
	@Override
	public void addProject(KyProject kyProject) {
		//调用添加方法
		kd.addProject(kyProject);
	}

	//删除项目
	@Override
	public int deleteProject(int pid) {
		
		return kd.deleteProject(pid);
	}

	//通过pid查询需要修改的科研项目
	@Override
	public KyProject showModifyProjectService(int pid) {
		
		return kd.showmodifyProjectDao(pid);
	}

	//通过pid查询修改科研项目
	@Override
	public int ChangeKyProjectService(int pid, String ptitle, String ptype) {
		
		return kd.ChangeKyProjectDao(pid,ptitle,ptype);
	}



}
