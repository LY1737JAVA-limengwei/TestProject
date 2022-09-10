package com.keyan.dao;

import java.util.List;
import java.util.Map;

import com.keyan.pojo.KyProject;

public interface KyProjectDao {
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
	 * 搜素项目功能
	 * @return
	 */
	List<KyProject> searchProjectService(String ptitle);
	
	/**
	 * 显示待审批的项目
	 * @return
	 */
	List<KyProject> pendingProjectService();

	/**
	 * 添加项目
	 * @param kyProject
	 */
	void addProject(KyProject kyProject);

	/**
	 * 删除一个项目
	 * @param pid
	 * @return
	 */
	int deleteProject(int pid);

	/**
	 * 审批项目
	 * @param pid
	 * @return
	 */
	int approvaProject(int pid,int pstate);

	/**
	 * 通过pid查询需要修改的科研项目
	 * @param pid
	 * @return
	 */
	KyProject showmodifyProjectDao(int pid);
	
	/**
	 * 通过pid修改科研项目
	 * @param pid
	 * @return
	 */
	public int ChangeKyProjectDao(int pid, String ptitle, String ptype);

}
