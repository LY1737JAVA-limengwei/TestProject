package com.keyan.service.impl;

import java.util.List;
import java.util.Map;

import com.keyan.dao.KyProjectDao;
import com.keyan.dao.impl.KyProjectDaoImpl;
import com.keyan.pojo.KyProject;
import com.keyan.service.KyProjectService;

public class KyProjectServiceImpl implements KyProjectService{
	//����Dao�����
	KyProjectDao kd = new KyProjectDaoImpl();
	
	//��ȡ���п�����Ŀ��Ϣ
	@Override
	public List<KyProject> projectShowService() {
		
		return kd.projectShowService();
	}
	
	//ajax��ȡ���п�����Ŀ��Ϣ
	@Override
	public List<Map<String, Object>> ajaxprojectShowService() {
		
		return kd.ajaxprojectShowService();
	}
	
	//��ʾ������������Ŀ��Ϣ
	@Override
	public List<KyProject> pendingProjectService() {
			
		return kd.pendingProjectService();
	}
	
	//������Ŀ����
	@Override
	public List<KyProject> searchService(String ptitle) {

		return kd.searchProjectService(ptitle);
	}
	
	//������Ŀ
	@Override
	public int approvaProject(int pid,int pstate) {
		
		return kd.approvaProject(pid,pstate);
	}

	//�����Ŀ
	@Override
	public void addProject(KyProject kyProject) {
		//������ӷ���
		kd.addProject(kyProject);
	}

	//ɾ����Ŀ
	@Override
	public int deleteProject(int pid) {
		
		return kd.deleteProject(pid);
	}

	//ͨ��pid��ѯ��Ҫ�޸ĵĿ�����Ŀ
	@Override
	public KyProject showModifyProjectService(int pid) {
		
		return kd.showmodifyProjectDao(pid);
	}

	//ͨ��pid��ѯ�޸Ŀ�����Ŀ
	@Override
	public int ChangeKyProjectService(int pid, String ptitle, String ptype) {
		
		return kd.ChangeKyProjectDao(pid,ptitle,ptype);
	}



}
