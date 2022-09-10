package com.keyan.service;

import java.util.List;

import com.keyan.pojo.Notice;


public interface NoticeService {

	/**
	 * 获取所有通知信息
	 * @return
	 */
	List<Notice> ShownoticeService();

	/**
	 * 添加新公告
	 * @param Notice
	 */
	void addNotice(Notice notice);
	
	/**
	 * 删除通知
	 * @param id
	 * @return
	 */
	int deleteNotice(int id);
	

	/**
	 * 根据id查询待修改修改公告信息
	 * @return
	 */
	Notice showModifyNoticeService(int id);

	/**
	 * 根据id修改公告信息
	 * @param id
	 * @param nitile
	 * @param ncontent
	 * @return
	 */
	int ChangeNoticeService(int id, String nitile, String ncontent);

	/**
	 * 搜索公告功能
	 * @param nitile
	 * @return
	 */
	List<Notice> searchService(String nitile);
}
