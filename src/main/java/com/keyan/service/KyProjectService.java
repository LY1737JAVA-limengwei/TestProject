package com.keyan.service;

import java.util.List;
import java.util.Map;

import com.keyan.pojo.KyProject;

public interface KyProjectService {

	/**
	 * ��ȡ���п�����Ŀ��Ϣ
	 * @return
	 */
	List<KyProject> projectShowService();
	
	/**
	 * ajax��ȡ���п�����Ŀ��Ϣ
	 * @return
	 */
	List<Map<String, Object>> ajaxprojectShowService();
	
	/**
	 * ��ʾ������������Ŀ��Ϣ
	 * @return
	 */
	List<KyProject> pendingProjectService();
	
	/**
	 * ������Ŀ����
	 * @return
	 */
	List<KyProject> searchService(String ptitle);
	
	/**
	 * ������Ŀ
	 * @param pid
	 * @return 
	 */
	int approvaProject(int pid,int pstate);
	
	/**
	 * �����Ŀ
	 * @param kyProject
	 */
	void addProject(KyProject kyProject);
	
	/**
	 * ɾ����Ŀ
	 * @param pid
	 * @return
	 */
	int deleteProject(int pid);

	/**
	 * ͨ��pid��ѯ��Ҫ�޸ĵĿ�����Ŀ
	 * @param pid
	 * @return
	 */
	KyProject showModifyProjectService(int pid);

	/**
	 * ͨ��pid�޸Ŀ�����Ŀ
	 * @param pid
	 * @param ptitle
	 * @param ptype
	 * @return
	 */
	int ChangeKyProjectService(int pid,String ptitle, String ptype);
	
}
