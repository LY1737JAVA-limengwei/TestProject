package com.keyan.dao;

import java.util.List;

import com.keyan.pojo.Notice;

public interface NoticeDao {
	/**
	 * 获取所有通知信息
	 * @return
	 */
	List<Notice> ShownoticeDao();

	/**
	 * 添加新公告
	 * @param notice
	 */
	void addNotice(Notice notice);

	/**
	 * 删除一个通知
	 * @param id
	 * @return
	 */
	int deleteNoticeDao(int id);
	
	/**
	 * 通过id查询修改科研项目
	 * @param id
	 * @return
	 */
	Notice showModifyNoticeDao(int id);
	
	/**
	 * 根据id修改公告信息
	 * @return
	 */
	int ChangeNoticeDao(int id, String nitile, String ncontent);

	
	/**
	 * 搜索公告功能
	 * @param nitile
	 * @return
	 */
	List<Notice> searchDao(String nitile);
}
