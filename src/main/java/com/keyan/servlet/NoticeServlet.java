package com.keyan.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.keyan.pojo.Admin;
import com.keyan.pojo.KyProject;
import com.keyan.pojo.Notice;
import com.keyan.pojo.User;
import com.keyan.service.NoticeService;
import com.keyan.service.impl.NoticeServiceImpl;
import com.mysql.cj.Session;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// 获取service层对象
	NoticeService noti = new NoticeServiceImpl();

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		// 获取操作符
		String oper = req.getParameter("oper");
		
		if ("showNotice".equals(oper)) {
			System.out.println("我来到了查看公告servlet");
			//调用查看公告功能
			showNotice(req, resp);
		} else if ("addNotice".equals(oper)) {
			System.out.println("我来到了添加公告servlet");
			// 调用新增公告功能
			addNotice(req, resp);
		} else if ("deleteNotice".equals(oper)) {
			// 调用删除公告功能
			System.out.println("我来到了删除公告servlet");
			deleteNotice(req, resp);
		} else if ("showmodifyNotice".equals(oper)) {
			// 调用修改公告功能1
			System.out.println("我来到了修改公告1servlet");
			showmodifyNotice(req, resp);
		} else if ("modifyNotice".equals(oper)) {
			// 调用修改公告功能2
			System.out.println("我来到了修改公告2servlet");
			modifyNotice(req, resp);
		} else if ("searchNoticeadmin".equals(oper) || "searchNoticeteacher".equals(oper)) {
			System.out.println("我来到了搜索公告servlet");
			// 调用搜索公告功能
			searchNotice(req, resp);
		}

	}

	//搜索公告功能
	private void searchNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nitile = req.getParameter("nitile");
		System.out.println("我来到了搜素功能,搜索内容为：" + nitile);
		
		//调用service处理
		List<Notice> showNotice = noti.searchService(nitile);
			if (showNotice != null) {
				//将查询到的用户数据存储到request对象
				req.setAttribute("showNotice", showNotice);
				
				//由于多个页面公用一个搜索功能，需让页面从哪来回哪去
					//接收操作符
					String oper = req.getParameter("oper"); 
					System.out.println("接收到的操作符" + oper);
					if("searchNoticeadmin".equals(oper)){ 
						//请求转发到showNoticeAA.jsp
						req.getRequestDispatcher("adminAA/noticeAA/showNoticeAA.jsp").forward(req, resp);
						return; 
					}else if ("searchNoticeteacher".equals(oper)) {
						//请求转发到showNoticeUU.jsp
						req.getRequestDispatcher("userUU/noticeUU/showNoticeUU.jsp").forward(req,resp);
						return; 
					}
				
			}
	}

	// 显示所有公告信息
	private List<Notice> showNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我来到了servlet中的查看公告信息方法");
		
		//调用service处理
		List<Notice> showNotice = noti.ShownoticeService();
		
		if (showNotice != null) {
			//将查询到的用户数据存储到request对象
			req.setAttribute("showNotice", showNotice);
			
			String yonghu = req.getParameter("yonghu");
			if ("admin".equals(yonghu)) {
				//请求转发
				req.getRequestDispatcher("adminAA/noticeAA/showNoticeAA.jsp").forward(req, resp);
				return showNotice;
			}else if("jiaoshi".equals(yonghu)){
				//请求转发
				req.getRequestDispatcher("userUU/noticeUU/showNoticeUU.jsp").forward(req, resp);
				return showNotice;
			}
			
		}
		return showNotice;
	}

	// 添加公告
	private List<Notice> addNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收前段传来的数据，并将其封装成Notice类
		String nitile = req.getParameter("nitile");//公告标题
		String ncontent = req.getParameter("ncontent");//公告内容
		// 获取当前登录账户名（即公告发布人）
		// 获取session对象
		HttpSession session = req.getSession();
		Admin name1 = (Admin)session.getAttribute("admin");
		String ndepartid = name1.getaname();
		
		// 获取当前系统时间（即公告发布时间）
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String ntime = df.format(new Date());
		
		//封装为 KyProject 类 
		Notice noc = new Notice(); 
		noc.setNitile(nitile);
		noc.setNcontent(ncontent);
		noc.setNtime(ntime);
		noc.setNdepartid(ndepartid);
		System.out.println("封装好的数据" + noc);
		// 调用service中的添加项目方法
		noti.addNotice(noc);
		
		//回到showNoticeAA.jsp页面
		List<Notice> showNotice = noti.ShownoticeService();
		
		if (showNotice != null) {
			//将查询到的用户数据存储到request对象
			req.setAttribute("showNotice", showNotice);
			//请求转发
			req.getRequestDispatcher("adminAA/mainAA/mainAA.jsp").forward(req, resp);
			//重定向/notice?oper=showNotice
			//resp.sendRedirect("adminAA/mainAA/leftAA.jsp");
			return showNotice;
		}
		return showNotice;
	}

	// 调用修改公告功能1
	private void showmodifyNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取session对象
		HttpSession session = req.getSession();
		
		//1 获取前段传来的需要修改的项目的id,并强制将String类型转为int类型
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("步骤一拿到的id" + id);
		//2 通过id调用service查询
		Notice showModifyNotice = noti.showModifyNoticeService(id);
		
		if (showModifyNotice != null) {
			//把项目信息存储到session会话中
			session.setAttribute("Notice",showModifyNotice);
			//请求转发
			req.getRequestDispatcher("adminAA/noticeAA/modifyNoticeAA.jsp").forward(req, resp);
		}
	}
	// 调用修改公告功能2
	private void modifyNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取新的公告标题和内容,以及被修改项目的id
		String nitile = req.getParameter("nitile");
		String ncontent = req.getParameter("ncontent");
		int id = Integer.parseInt(req.getParameter("id"));
		//处理请求
			//调用service处理
			int index = noti.ChangeNoticeService(id,nitile,ncontent);
			if(index > 0) {
			//调用service处理,查询所有项目，返回到显示所有项目页面
				List<Notice> showNotice = noti.ShownoticeService();
			
			if (showNotice != null) {
				//将查询到的用户数据存储到request对象
				req.setAttribute("showNotice", showNotice);
				//请求转发
				req.getRequestDispatcher("adminAA/mainAA/mainAA.jsp").forward(req, resp);
			}
		}
	}
	
	// 删除项目
	private int deleteNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收jsp传来的pid,并强制将String类型转为int类型
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("获取被删除项目pid：" + id);
		//调用service删除方法
		noti.deleteNotice(id);
		// 回到showNotice.jsp页面
		List<Notice> showNotice = noti.ShownoticeService();
		if (showNotice != null) { 
			// 将查询到的用户数据存储到request对象
			req.setAttribute("showNotice", showNotice); 
			// 请求转发
			req.getRequestDispatcher("adminAA/noticeAA/showNoticeAA.jsp").forward(req, resp); 
		}
		return 0;
	}

}
