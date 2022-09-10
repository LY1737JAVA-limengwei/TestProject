package com.keyan.service.impl;

import java.util.List;

import com.keyan.dao.NoticeDao;
import com.keyan.dao.impl.NoticeDaoImpl;
import com.keyan.pojo.Notice;
import com.keyan.service.NoticeService;

public class NoticeServiceImpl implements NoticeService{
	//声明Dao层对象
	NoticeDao ni = new NoticeDaoImpl();
	
	//获取所有通知信息
	@Override
	public List<Notice> ShownoticeService() {
		
		return ni.ShownoticeDao();
	}

	//添加新公告
	@Override
	public void addNotice(Notice notice) {
		//调用添加方法
		ni.addNotice(notice);
	}

	//删除项目
	@Override
	public int deleteNotice(int id) {
		
		return ni.deleteNoticeDao(id);
	}

	//根据id查询待修改修改公告信息
	@Override
	public Notice showModifyNoticeService(int id) {
		
		return ni.showModifyNoticeDao(id);
	}
	//根据id修改公告信息
	@Override
	public int ChangeNoticeService(int id, String nitile, String ncontent) {
		
		return ni.ChangeNoticeDao(id,nitile,ncontent);
	}

	//搜索公告功能
	@Override
	public List<Notice> searchService(String nitile) {
		
		return ni.searchDao(nitile);
	}


}
