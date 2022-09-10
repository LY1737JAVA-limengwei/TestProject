package com.keyan.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.keyan.service.AdminService;
import com.keyan.service.UserService;
import com.keyan.service.impl.AdminServiceImpl;
import com.keyan.service.impl.UserServiceImpl;
import com.mysql.cj.Session;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
	UserService us = new UserServiceImpl();
	AdminService as = new AdminServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取操作符
		String oper=req.getParameter("oper");
		String identity = req.getParameter("identity");
		System.out.println("传来的登录身份" + identity);
		if("login".equals(oper)) {
			//调用登录处理方法
			if ("teacher".equals(identity)) {
				checkUserLogin(req,resp);
			}else if ("admin".equals(identity)) {
				checkadminLogin(req,resp);
			}
			
		}else if ("out".equals(oper)) {
			//调用退出功能
			userOut(req,resp);
		}else if ("showUser".equals(oper)) {
			//调用查看所有用户信息功能
			System.out.println("显示所有用户信息功能"+oper);
			showUser(req,resp);
		}else if ("adduser".equals(oper)) {
			//调用添加账号功能
			addUser(req,resp);
		}else if ("deleteUser".equals(oper)) {
			//调用删除账号功能
			deleteUser(req,resp);
		}else if ("showMmodifyUser".equals(oper)) {
			//调用管理员吗修改教师用户的密码功能1
			showMmodifyUser(req,resp);
		}else if ("modifyuserpwd".equals(oper)) {
			//调用管理员吗修改教师用户的密码功能2
			modifyuserpwd(req,resp);
		}else if ("alterpwd".equals(oper) || "alteradminpwd".equals(oper)) {
			//调用本人修改（密码）功能
			userChangePwd(req,resp);
		}else if ("reg".equals(oper)) {
			//调用注册功能
		}else {
			System.out.println("没有找到对应的操作，"+oper);
		}
	}

	

	//调用管理员吗修改教师用户的密码功能1
	private void showMmodifyUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取session对象
		HttpSession session = req.getSession();
		
		//1 获取前段传来的需要修改的账号的uid,并强制将String类型转为int类型
		int uid = Integer.parseInt(req.getParameter("uid"));
		System.out.println("步骤一拿到的uid" + uid);
		//2 通过id调用service查询
		User showMmodifyUser = us.showMmodifyUserService(uid);
		
		if (showMmodifyUser != null) {
			//把项目信息存储到session会话中
			session.setAttribute("User",showMmodifyUser);
			//请求转发
			req.getRequestDispatcher("adminAA/userAA/pwdAA.jsp").forward(req, resp);
		}
	}

	//调用管理员吗修改教师用户的密码功能2
	private void modifyuserpwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String modifyuser = req.getParameter("modifyuser");
		String modifypwd = req.getParameter("modifypwd");
		int uid = Integer.parseInt(req.getParameter("uid"));
		//处理请求
			//调用service修改处理
			int index = us.modifyuserpwdService(uid,modifyuser,modifypwd);
			
			if(index > 0) {
			//调用service处理,查询所有项目，返回到显示所有项目页面
			List<User> showAllUser = us.userShowService();
			
			if (showAllUser != null) {
				//将查询到的用户数据存储到request对象
				req.setAttribute("showAllUser", showAllUser);
				//请求转发
				req.getRequestDispatcher("adminAA/mainAA/mainAA.jsp").forward(req, resp);
			}
		}
	}
	
	//处理管理员登录
	private void checkadminLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//获取session对象
		HttpSession session = req.getSession();
		
		//获取请求信息
		String aname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String identity = req.getParameter("identity");
			//将身份信息存放到session中
			session.setAttribute("permission", identity);
		
		//处理请求信息
			//校验
			Admin a = as.checkAdminLoginService(aname, pwd);
			if (a != null) {
				//把用户信息存储到session会话中
				session.setAttribute("admin",a);
				System.out.println("登录时存入的session的登录信息" + session.getAttribute("admin"));
				/*
				 * session.setAttribute("uid", u.getUid()); 
				 * session.setAttribute("uname",u.getUname()); 
				 * session.setAttribute("upwd", u.getPwd());
				 */
				//设置session失效时间
				session.setMaxInactiveInterval(12*60*60);
				//重定向
				resp.sendRedirect("../day07/adminAA/mainAA/mainAA.jsp");
				return;
				
					/*if ("admin".equals(identity)) {
							
						} 
					 * else { //添加标识符到requset中 req.setAttribute("flag", 0);
					 * session.setAttribute("logfailed", "身份与账号信息不匹配"); //请求转发
					 * req.getRequestDispatcher("/login.jsp").forward(req, resp); return; }
					 */
				
			}else {
				//添加标识符到requset中
				req.setAttribute("flag", 0);
				session.setAttribute("logfailed", "用户名或密码错误");
				//请求转发
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				return;
			}
	}

	//处理教师（用户）登录
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//获取session对象
		HttpSession session = req.getSession();
		
		//获取请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String identity = req.getParameter("identity");
		//将身份信息存放到session中
		session.setAttribute("permission", identity);
		
		//处理请求信息
			//校验
			User u = us.checkUserLoginService(uname, pwd);
			if (u != null) {
				
				//把用户信息存储到session会话中
				session.setAttribute("user",u);
				/*
				 * session.setAttribute("uid", u.getUid()); 
				 * session.setAttribute("uname",u.getUname()); 
				 * session.setAttribute("upwd", u.getPwd());
				 */
				//设置session失效时间
				session.setMaxInactiveInterval(12*60*60);
				//重定向
				 resp.sendRedirect("../day07/userUU/mainUU/mainUU.jsp"); 
				 return; 
				 		/*if("teacher".equals(identity)) { 
					 
							} 
						 * else { //添加标识符到requset中 req.setAttribute("flag", 0);
						 * session.setAttribute("logfailed", "身份与账号信息不匹配"); //请求转发
						 * req.getRequestDispatcher("/login.jsp").forward(req, resp); return; }
						 */
				
			}else {
				//添加标识符到requset中
				req.setAttribute("flag", 0);
				session.setAttribute("logfailed", "用户名或密码错误");
				//请求转发
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				return;
			}
	}

	//用户退出
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取session对象
		HttpSession session1=req.getSession();
		//强制销毁session
		session1.invalidate();
		//重定向到登录页面
		resp.sendRedirect("/day07/login.jsp");
	}

	//显示所有用户信息
	private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理请求
			//调用service处理
			List<User> showAllUser = us.userShowService();
			if (showAllUser != null) {
				//将查询到的用户数据存储到request对象
				req.setAttribute("showAllUser", showAllUser);
				
				//获取session对象
				HttpSession session = req.getSession();
				//判断当前用户身份
				String permission = (String)session.getAttribute("permission");
				System.out.println("注意看，这是session里拿到的身份" + permission);
				
				if ("teacher".equals(permission)) {
					//如果是教师查看信息转发到教师界面
					req.getRequestDispatcher("userUU/yonghuUU/showUserUU.jsp").forward(req, resp);
					return;
				}else if ("admin".equals(permission)) {
					//如果是管理员查看信息转发到管理员界面
					req.getRequestDispatcher("adminAA/userAA/showUserAA.jsp").forward(req, resp);
					return;
				}
				
				
			}
	}	

	//调用添加账号功能
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收前段传来的数据，并将其封装成User类
		String uname = req.getParameter("addusername");
		String pwd = req.getParameter("adduserpwd");
		
		//封装为	User 类	
		User addu = new User();
		addu.setUname(uname);
		addu.setPwd(pwd);
		//调用service中的添加项目方法
		us.addUser(addu);
		
		
		//回到showUserAA.jsp页面
		List<User> showAllUser = us.userShowService();
		if (showAllUser != null) {
			//将查询到的用户数据存储到request对象
			req.setAttribute("showAllUser", showAllUser);
			
			//请求转发
			//req.getRequestDispatcher("userUU/mainUU/mainUU.jsp").forward(req, resp);
			req.getRequestDispatcher("adminAA/mainAA/mainAA.jsp").forward(req, resp);
			 //重定向
			 //resp.sendRedirect("/day07/userUU/kyProjectUU/mainUU/mainUU.jsp");
			 
		}
	}
	
	//调用删除账号功能
	private int deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收jsp传来的uid,并强制将String类型转为int类型
		int uid = Integer.parseInt(req.getParameter("uid"));
		System.out.println("获取被删除项目pid：" + uid);
		//调用service删除方法
		us.deleteUser(uid);
		// 回到showUserAA.jsp页面
		List<User> showAllUser = us.userShowService();
		if (showAllUser != null) {
			//将查询到的用户数据存储到request对象
			req.setAttribute("showAllUser", showAllUser);
			
			//请求转发
			//req.getRequestDispatcher("userUU/mainUU/mainUU.jsp").forward(req, resp);
			req.getRequestDispatcher("adminAA/userAA/showUserAA.jsp").forward(req, resp);
			 //重定向
			 //resp.sendRedirect("/day07/userUU/kyProjectUU/mainUU/mainUU.jsp");
			 
		}
		return 0;
	}
	
	//修改本人密码
	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取教师新密码
		String newPwd = req.getParameter("newPwd");
		//获取管理员新密码
		String newadminPwd = req.getParameter("newadminPwd");
		
		//处理请求
			//调用service处理
			String oper = req.getParameter("oper");
			if ("alterpwd".equals(oper)) {
				int uid = Integer.parseInt(req.getParameter("uid"));
				System.out.println("我来到了修改教师本人密码");
				System.out.println("信息"+uid+newadminPwd);
				//调用教师本人修改（密码）功能
				int index = us.userChangePwdService(uid,newPwd);
				if(index > 0) {
					//获取session对象
					HttpSession session2=req.getSession();
					session2.setAttribute("altsuccess", "教师密码修改成功");
					resp.sendRedirect("/day07/login.jsp");
				}
			}else if ("alteradminpwd".equals(oper)) {
				int aid = Integer.parseInt(req.getParameter("aid"));
				System.out.println("我来到了修改管理员本人密码");
				System.out.println("信息"+aid+newadminPwd);
				//调用管理员本人修改（密码）功能
				int index1 = us.adminChangePwdService(aid,newadminPwd);
				if(index1 > 0) {
					//获取session对象
					HttpSession session2=req.getSession();
					session2.setAttribute("altsuccess", "管理员密码修改成功");
					resp.sendRedirect("/day07/login.jsp");
				}
			}
		
	}
		
}
