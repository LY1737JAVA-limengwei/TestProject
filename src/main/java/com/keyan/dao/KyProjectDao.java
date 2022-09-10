package com.keyan.dao;

import java.util.List;
import java.util.Map;

import com.keyan.pojo.KyProject;

public interface KyProjectDao {
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
	 * ������Ŀ����
	 * @return
	 */
	List<KyProject> searchProjectService(String ptitle);
	
	/**
	 * ��ʾ����������Ŀ
	 * @return
	 */
	List<KyProject> pendingProjectService();

	/**
	 * �����Ŀ
	 * @param kyProject
	 */
	void addProject(KyProject kyProject);

	/**
	 * ɾ��һ����Ŀ
	 * @param pid
	 * @return
	 */
	int deleteProject(int pid);

	/**
	 * ������Ŀ
	 * @param pid
	 * @return
	 */
	int approvaProject(int pid,int pstate);

	/**
	 * ͨ��pid��ѯ��Ҫ�޸ĵĿ�����Ŀ
	 * @param pid
	 * @return
	 */
	KyProject showmodifyProjectDao(int pid);
	
	/**
	 * ͨ��pid�޸Ŀ�����Ŀ
	 * @param pid
	 * @return
	 */
	public int ChangeKyProjectDao(int pid, String ptitle, String ptype);

}
