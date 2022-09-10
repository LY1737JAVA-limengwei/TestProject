package com.keyan.dao;

import java.util.List;

import com.keyan.pojo.Notice;

public interface NoticeDao {
	/**
	 * ��ȡ����֪ͨ��Ϣ
	 * @return
	 */
	List<Notice> ShownoticeDao();

	/**
	 * ����¹���
	 * @param notice
	 */
	void addNotice(Notice notice);

	/**
	 * ɾ��һ��֪ͨ
	 * @param id
	 * @return
	 */
	int deleteNoticeDao(int id);
	
	/**
	 * ͨ��id��ѯ�޸Ŀ�����Ŀ
	 * @param id
	 * @return
	 */
	Notice showModifyNoticeDao(int id);
	
	/**
	 * ����id�޸Ĺ�����Ϣ
	 * @return
	 */
	int ChangeNoticeDao(int id, String nitile, String ncontent);

	
	/**
	 * �������湦��
	 * @param nitile
	 * @return
	 */
	List<Notice> searchDao(String nitile);
}
