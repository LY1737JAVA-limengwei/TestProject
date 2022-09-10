package com.keyan.service;

import java.util.List;

import com.keyan.pojo.Notice;


public interface NoticeService {

	/**
	 * ��ȡ����֪ͨ��Ϣ
	 * @return
	 */
	List<Notice> ShownoticeService();

	/**
	 * ����¹���
	 * @param Notice
	 */
	void addNotice(Notice notice);
	
	/**
	 * ɾ��֪ͨ
	 * @param id
	 * @return
	 */
	int deleteNotice(int id);
	

	/**
	 * ����id��ѯ���޸��޸Ĺ�����Ϣ
	 * @return
	 */
	Notice showModifyNoticeService(int id);

	/**
	 * ����id�޸Ĺ�����Ϣ
	 * @param id
	 * @param nitile
	 * @param ncontent
	 * @return
	 */
	int ChangeNoticeService(int id, String nitile, String ncontent);

	/**
	 * �������湦��
	 * @param nitile
	 * @return
	 */
	List<Notice> searchService(String nitile);
}
