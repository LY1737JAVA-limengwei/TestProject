package com.keyan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.keyan.pojo.User;
import com.keyan.pojo.KyProject;
import com.keyan.service.KyProjectService;
import com.keyan.service.impl.KyProjectServiceImpl;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class KyProjectServlet
 */
@WebServlet("/KyProjectServlet")
public class KyProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KyProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	//获取service层对象
	KyProjectService kyp = new KyProjectServiceImpl();
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取操作符
		String oper = req.getParameter("oper");
		if("showProject".equals(oper)) {
			//调用查看所有项目信息功能
			showProject(req,resp);
		}else if ("addProject".equals(oper)) {
			//调用新增项目功能
			addProject(req,resp);
		}else if ("pendingProject".equals(oper)) {
			//调用查看待审批项目功能
			pendingProject(req,resp);
		}else if ("showProjectsearchadmin".equals(oper) || "showProjectsearchuser".equals(oper) || "pendingsearch".equals(oper)) {
			//调用搜素项目功能
			searchProject(req,resp);
		}else if ("adopt".equals(oper) || "fail".equals(oper)) {
			System.out.println("我来到了审批servlet");
			//调用审批项目功能
			approvalProject(req,resp);
		}else if ("deleteProject".equals(oper)) {
			//调用删除项目功能
			deleteProject(req,resp);
		}else if ("modifyProject1".equals(oper)) {
			//调用修改项目功能1
			modifyProject1(req,resp);
		}else if ("modifyProject2".equals(oper)) {
			//调用修改项目功能2
			modifyProject2(req,resp);
		}else if ("ajax".equals(oper)) {
			//调用ajaxProject
			System.out.println("我来到了servlet中的ajax判定");
			ajaxProject(req,resp);
		}
			  
	}


	//调用修改项目功能1
	private void modifyProject1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取session对象
		HttpSession session = req.getSession();
		
		//1 获取前段传来的需要修改的项目的pid,并强制将String类型转为int类型
		int pid = Integer.parseInt(req.getParameter("pid"));
		//2 调用service查询
		KyProject showModifyProject = kyp.showModifyProjectService(pid);
		
		if (showModifyProject != null) {
			//把项目信息存储到session会话中
			session.setAttribute("KyProject",showModifyProject);
			//请求转发
			req.getRequestDispatcher("adminAA/kyProjectAA/modifyProjectAA.jsp").forward(req, resp);
		}
	}
	//调用修改项目功能2,即可完成修改
	private void modifyProject2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取新的项目标题和类型,以及被修改项目的pid
		String ptitle = req.getParameter("ptitle");
		String ptype = req.getParameter("ptype");
		int pid = Integer.parseInt(req.getParameter("pid"));
		//处理请求
			//调用service处理
		int index = kyp.ChangeKyProjectService(pid,ptitle,ptype);
		if(index > 0) {
			//调用service处理,查询所有项目，返回到显示所有项目页面
			List<KyProject> showProject = kyp.projectShowService();
			
			if (showProject != null) {
				//将查询到的用户数据存储到request对象
				req.setAttribute("showProject", showProject);
				//请求转发
				req.getRequestDispatcher("adminAA/mainAA/mainAA.jsp").forward(req, resp);
			}
			//请求转发
			//req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
			//resp.sendRedirect("/day07/adminAA/kyProjectAA/showProjectAA.jsp");
		}
		
	}
	
	//调用ajaxProject
	private void ajaxProject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("我来到了servlet中的ajax执行方法");
		
		//1.调用service查询
		List<Map<String, Object>> brands = kyp.ajaxprojectShowService(); 
		//System.out.println("查询到的数据" + showProject);
		
		//2.将集合转换为jaon数据 也称序列化,并
		/*
		 * resp.setContentType("application/json;charset=utf-8"); PrintWriter out =
		 * resp.getWriter(); out.write(JSONArray.fromObject(brands).toString());
		 * out.flush(); out.close();
		 */
		 
		 String jsonString = com.alibaba.fastjson.JSON.toJSONString(brands);
		 resp.getWriter().write(jsonString);
		
		 
		System.out.println("servlet执行完毕");
	}

	//显示所有科研项目信息
	private List<KyProject> showProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理请求
			//调用service处理
			List<KyProject> showProject = kyp.projectShowService();
			
			if (showProject != null) {
				//将查询到的用户数据存储到request对象
				req.setAttribute("showProject", showProject);
				
				String yonghu = req.getParameter("yonghu");
				System.out.println(yonghu);
				if ("admin".equals(yonghu)) {
					//请求转发
					req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
					return showProject;
				}else if("jiaoshi".equals(yonghu)){
					//请求转发
					req.getRequestDispatcher("userUU/kyProjectUU/showProjectUU.jsp").forward(req, resp);
					return showProject;
				}
				
			}
			return showProject;
	}

	//调用搜素项目功能
	private void searchProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ptitle = req.getParameter("ptitle");
		System.out.println("我来到了搜素功能,搜索内容为：" + ptitle);
		//调用service处理
		List<KyProject> showProject = kyp.searchService(ptitle);
		List<KyProject> pendingProject = kyp.searchService(ptitle);
			if (showProject != null) {
				//将查询到的用户数据存储到request对象
				req.setAttribute("showProject", showProject);
				req.setAttribute("pendingProject", pendingProject);
				
				//由于多个页面公用一个搜索功能，需让页面从哪来回哪去
					//接收操作符showProjectsearchadmin
					String oper = req.getParameter("oper");
				if("showProjectsearchadmin".equals(oper)) {
					//请求转发到showProject.jsp
					req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
					return;
				}else if ("showProjectsearchuser".equals(oper)) {
					//请求转发到pendingAA.jsp
					req.getRequestDispatcher("userUU/kyProjectUU/showProjectUU.jsp").forward(req, resp);
					return;
				}else if ("pendingsearch".equals(oper)) {
					//请求转发到pendingAA.jsp
					req.getRequestDispatcher("adminAA/kyProjectAA/pendingAA.jsp").forward(req, resp);
					return;
				}
				
			}
	}
	
	//查看待审批项目功能
	private int pendingProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理请求
		//调用service处理
		List<KyProject> pendingProject = kyp.pendingProjectService();
		
		if (pendingProject != null) {
			//将查询到的用户数据存储到request对象
			req.setAttribute("pendingProject", pendingProject);
			//请求转发
			req.getRequestDispatcher("adminAA/kyProjectAA/pendingAA.jsp").forward(req, resp);
		}
		return 0;
		
	}
	
	//审批项目功能
	private int approvalProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收jsp传来的pid,并强制将String类型转为int类型int
		System.out.println("我来到了审批功能");
		int pstate = 1;
		int pid = Integer.parseInt(req.getParameter("pid"));
		String approva = req.getParameter("oper");
		if("adopt".equals(approva)) {
			//adopt表示通过，给pstate赋值为2
			pstate = 2;
		}else if ("fail".equals(approva)) {
			//fail表示不通过，给pstate赋值为3
			pstate = 3;
		}
		System.out.println("获取被审批项目pid：" + pid + "和修改后的审批状态" + pstate);
		
		//调用service审批方法
		kyp.approvaProject(pid,pstate);
		
			//回到showProjectAA.jsp页面
			List<KyProject> showProject = kyp.projectShowService();
			if (showProject != null) {
				//将查询到的用户数据存储到request对象
				req.setAttribute("showProject", showProject);
				//请求转发
				req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
			}
			return 0;
	}
	
	//添加项目
	private String addProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收前段传来的数据，并将其封装成KyProject类
			String ptitle = req.getParameter("ptitle");
			String ptype = req.getParameter("ptype");
			//获取当前登录账户名（即项目申报人）
				//获取session对象
				HttpSession session = req.getSession();
				
			User apname1 = (User)session.getAttribute("user");
			String apname = apname1.getUname();
			//设置审批人（新增项目为 暂无）
			String exname = "暂无";
			//设置科研项目状态（默认为待审批）
			int pstate = 1;
			//获取当前系统时间（即项目申报时间）
				//设置日期格式
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String ptime = df.format(new Date());
		//封装为	KyProject 类	
		KyProject ky = new KyProject();
		ky.setApname(apname);
		ky.setExname(exname);
		ky.setPstate(pstate);
		ky.setPtitle(ptitle);
		ky.setPtype(ptype);
		ky.setPtime(ptime);
		//调用service中的添加项目方法
		kyp.addProject(ky);
		
		
		//回到showProjectUU.jsp页面
		List<KyProject> showProject = kyp.projectShowService();
		if (showProject != null) {
			//将查询到的用户数据存储到request对象
			req.setAttribute("showProject", showProject);
			
			//请求转发 req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
			//req.getRequestDispatcher("userUU/mainUU/mainUU.jsp").forward(req, resp);
			req.getRequestDispatcher("userUU/kyProjectUU/showProjectUU.jsp").forward(req, resp);
			 //重定向
			 //resp.sendRedirect("/day07/userUU/kyProjectUU/mainUU/mainUU.jsp");
			 
		}
		return null;
	}
	
	//删除项目
	private int deleteProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收jsp传来的pid,并强制将String类型转为int类型
		int pid = Integer.parseInt(req.getParameter("pid"));
		System.out.println("获取被删除项目pid：" + pid);
		//调用service删除方法
		kyp.deleteProject(pid);
			//回到showProject.jsp页面
			List<KyProject> showProject = kyp.projectShowService();
			if (showProject != null) {
				//将查询到的用户数据存储到request对象
				req.setAttribute("showProject", showProject);
				//请求转发
				req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
			}
		return 0;
	}
	
	
	
}
