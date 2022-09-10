package com.keyan.service.impl;

import java.util.List;

import com.keyan.dao.NoticeDao;
import com.keyan.dao.impl.NoticeDaoImpl;
import com.keyan.pojo.Notice;
import com.keyan.service.NoticeService;

public class NoticeServiceImpl implements NoticeService{
	//����Dao�����
	NoticeDao ni = new NoticeDaoImpl();
	
	//��ȡ����֪ͨ��Ϣ
	@Override
	public List<Notice> ShownoticeService() {
		
		return ni.ShownoticeDao();
	}

	//����¹���
	@Override
	public void addNotice(Notice notice) {
		//������ӷ���
		ni.addNotice(notice);
	}

	//ɾ����Ŀ
	@Override
	public int deleteNotice(int id) {
		
		return ni.deleteNoticeDao(id);
	}

	//����id��ѯ���޸��޸Ĺ�����Ϣ
	@Override
	public Notice showModifyNoticeService(int id) {
		
		return ni.showModifyNoticeDao(id);
	}
	//����id�޸Ĺ�����Ϣ
	@Override
	public int ChangeNoticeService(int id, String nitile, String ncontent) {
		
		return ni.ChangeNoticeDao(id,nitile,ncontent);
	}

	//�������湦��
	@Override
	public List<Notice> searchService(String nitile) {
		
		return ni.searchDao(nitile);
	}


}
