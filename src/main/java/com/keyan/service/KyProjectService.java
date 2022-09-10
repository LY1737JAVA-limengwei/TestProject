package com.keyan.service;

import java.util.List;
import java.util.Map;

import com.keyan.pojo.KyProject;

public interface KyProjectService {

	/**
	 * 获取所有科研项目信息
	 * @return
	 */
	List<KyProject> projectShowService();
	
	/**
	 * ajax获取所有科研项目信息
	 * @return
	 */
	List<Map<String, Object>> ajaxprojectShowService();
	
	/**
	 * 显示待审批科研项目信息
	 * @return
	 */
	List<KyProject> pendingProjectService();
	
	/**
	 * 搜素项目功能
	 * @return
	 */
	List<KyProject> searchService(String ptitle);
	
	/**
	 * 审批项目
	 * @param pid
	 * @return 
	 */
	int approvaProject(int pid,int pstate);
	
	/**
	 * 添加项目
	 * @param kyProject
	 */
	void addProject(KyProject kyProject);
	
	/**
	 * 删除项目
	 * @param pid
	 * @return
	 */
	int deleteProject(int pid);

	/**
	 * 通过pid查询需要修改的科研项目
	 * @param pid
	 * @return
	 */
	KyProject showModifyProjectService(int pid);

	/**
	 * 通过pid修改科研项目
	 * @param pid
	 * @param ptitle
	 * @param ptype
	 * @return
	 */
	int ChangeKyProjectService(int pid,String ptitle, String ptype);
	
}
