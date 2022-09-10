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
	
	
	//��ȡservice�����
	UserService us = new UserServiceImpl();
	AdminService as = new AdminServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//������������ʽ
		req.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
		resp.setContentType("text/html;charset=utf-8");
		//��ȡ������
		String oper=req.getParameter("oper");
		String identity = req.getParameter("identity");
		System.out.println("�����ĵ�¼���" + identity);
		if("login".equals(oper)) {
			//���õ�¼������
			if ("teacher".equals(identity)) {
				checkUserLogin(req,resp);
			}else if ("admin".equals(identity)) {
				checkadminLogin(req,resp);
			}
			
		}else if ("out".equals(oper)) {
			//�����˳�����
			userOut(req,resp);
		}else if ("showUser".equals(oper)) {
			//���ò鿴�����û���Ϣ����
			System.out.println("��ʾ�����û���Ϣ����"+oper);
			showUser(req,resp);
		}else if ("adduser".equals(oper)) {
			//��������˺Ź���
			addUser(req,resp);
		}else if ("deleteUser".equals(oper)) {
			//����ɾ���˺Ź���
			deleteUser(req,resp);
		}else if ("showMmodifyUser".equals(oper)) {
			//���ù���Ա���޸Ľ�ʦ�û������빦��1
			showMmodifyUser(req,resp);
		}else if ("modifyuserpwd".equals(oper)) {
			//���ù���Ա���޸Ľ�ʦ�û������빦��2
			modifyuserpwd(req,resp);
		}else if ("alterpwd".equals(oper) || "alteradminpwd".equals(oper)) {
			//���ñ����޸ģ����룩����
			userChangePwd(req,resp);
		}else if ("reg".equals(oper)) {
			//����ע�Ṧ��
		}else {
			System.out.println("û���ҵ���Ӧ�Ĳ�����"+oper);
		}
	}

	

	//���ù���Ա���޸Ľ�ʦ�û������빦��1
	private void showMmodifyUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡsession����
		HttpSession session = req.getSession();
		
		//1 ��ȡǰ�δ�������Ҫ�޸ĵ��˺ŵ�uid,��ǿ�ƽ�String����תΪint����
		int uid = Integer.parseInt(req.getParameter("uid"));
		System.out.println("����һ�õ���uid" + uid);
		//2 ͨ��id����service��ѯ
		User showMmodifyUser = us.showMmodifyUserService(uid);
		
		if (showMmodifyUser != null) {
			//����Ŀ��Ϣ�洢��session�Ự��
			session.setAttribute("User",showMmodifyUser);
			//����ת��
			req.getRequestDispatcher("adminAA/userAA/pwdAA.jsp").forward(req, resp);
		}
	}

	//���ù���Ա���޸Ľ�ʦ�û������빦��2
	private void modifyuserpwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String modifyuser = req.getParameter("modifyuser");
		String modifypwd = req.getParameter("modifypwd");
		int uid = Integer.parseInt(req.getParameter("uid"));
		//��������
			//����service�޸Ĵ���
			int index = us.modifyuserpwdService(uid,modifyuser,modifypwd);
			
			if(index > 0) {
			//����service����,��ѯ������Ŀ�����ص���ʾ������Ŀҳ��
			List<User> showAllUser = us.userShowService();
			
			if (showAllUser != null) {
				//����ѯ�����û����ݴ洢��request����
				req.setAttribute("showAllUser", showAllUser);
				//����ת��
				req.getRequestDispatcher("adminAA/mainAA/mainAA.jsp").forward(req, resp);
			}
		}
	}
	
	//�������Ա��¼
	private void checkadminLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//��ȡsession����
		HttpSession session = req.getSession();
		
		//��ȡ������Ϣ
		String aname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String identity = req.getParameter("identity");
			//�������Ϣ��ŵ�session��
			session.setAttribute("permission", identity);
		
		//����������Ϣ
			//У��
			Admin a = as.checkAdminLoginService(aname, pwd);
			if (a != null) {
				//���û���Ϣ�洢��session�Ự��
				session.setAttribute("admin",a);
				System.out.println("��¼ʱ�����session�ĵ�¼��Ϣ" + session.getAttribute("admin"));
				/*
				 * session.setAttribute("uid", u.getUid()); 
				 * session.setAttribute("uname",u.getUname()); 
				 * session.setAttribute("upwd", u.getPwd());
				 */
				//����sessionʧЧʱ��
				session.setMaxInactiveInterval(12*60*60);
				//�ض���
				resp.sendRedirect("../day07/adminAA/mainAA/mainAA.jsp");
				return;
				
					/*if ("admin".equals(identity)) {
							
						} 
					 * else { //��ӱ�ʶ����requset�� req.setAttribute("flag", 0);
					 * session.setAttribute("logfailed", "������˺���Ϣ��ƥ��"); //����ת��
					 * req.getRequestDispatcher("/login.jsp").forward(req, resp); return; }
					 */
				
			}else {
				//��ӱ�ʶ����requset��
				req.setAttribute("flag", 0);
				session.setAttribute("logfailed", "�û������������");
				//����ת��
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				return;
			}
	}

	//�����ʦ���û�����¼
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//��ȡsession����
		HttpSession session = req.getSession();
		
		//��ȡ������Ϣ
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String identity = req.getParameter("identity");
		//�������Ϣ��ŵ�session��
		session.setAttribute("permission", identity);
		
		//����������Ϣ
			//У��
			User u = us.checkUserLoginService(uname, pwd);
			if (u != null) {
				
				//���û���Ϣ�洢��session�Ự��
				session.setAttribute("user",u);
				/*
				 * session.setAttribute("uid", u.getUid()); 
				 * session.setAttribute("uname",u.getUname()); 
				 * session.setAttribute("upwd", u.getPwd());
				 */
				//����sessionʧЧʱ��
				session.setMaxInactiveInterval(12*60*60);
				//�ض���
				 resp.sendRedirect("../day07/userUU/mainUU/mainUU.jsp"); 
				 return; 
				 		/*if("teacher".equals(identity)) { 
					 
							} 
						 * else { //��ӱ�ʶ����requset�� req.setAttribute("flag", 0);
						 * session.setAttribute("logfailed", "������˺���Ϣ��ƥ��"); //����ת��
						 * req.getRequestDispatcher("/login.jsp").forward(req, resp); return; }
						 */
				
			}else {
				//��ӱ�ʶ����requset��
				req.setAttribute("flag", 0);
				session.setAttribute("logfailed", "�û������������");
				//����ת��
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				return;
			}
	}

	//�û��˳�
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//��ȡsession����
		HttpSession session1=req.getSession();
		//ǿ������session
		session1.invalidate();
		//�ض��򵽵�¼ҳ��
		resp.sendRedirect("/day07/login.jsp");
	}

	//��ʾ�����û���Ϣ
	private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��������
			//����service����
			List<User> showAllUser = us.userShowService();
			if (showAllUser != null) {
				//����ѯ�����û����ݴ洢��request����
				req.setAttribute("showAllUser", showAllUser);
				
				//��ȡsession����
				HttpSession session = req.getSession();
				//�жϵ�ǰ�û����
				String permission = (String)session.getAttribute("permission");
				System.out.println("ע�⿴������session���õ������" + permission);
				
				if ("teacher".equals(permission)) {
					//����ǽ�ʦ�鿴��Ϣת������ʦ����
					req.getRequestDispatcher("userUU/yonghuUU/showUserUU.jsp").forward(req, resp);
					return;
				}else if ("admin".equals(permission)) {
					//����ǹ���Ա�鿴��Ϣת��������Ա����
					req.getRequestDispatcher("adminAA/userAA/showUserAA.jsp").forward(req, resp);
					return;
				}
				
				
			}
	}	

	//��������˺Ź���
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//����ǰ�δ��������ݣ��������װ��User��
		String uname = req.getParameter("addusername");
		String pwd = req.getParameter("adduserpwd");
		
		//��װΪ	User ��	
		User addu = new User();
		addu.setUname(uname);
		addu.setPwd(pwd);
		//����service�е������Ŀ����
		us.addUser(addu);
		
		
		//�ص�showUserAA.jspҳ��
		List<User> showAllUser = us.userShowService();
		if (showAllUser != null) {
			//����ѯ�����û����ݴ洢��request����
			req.setAttribute("showAllUser", showAllUser);
			
			//����ת��
			//req.getRequestDispatcher("userUU/mainUU/mainUU.jsp").forward(req, resp);
			req.getRequestDispatcher("adminAA/mainAA/mainAA.jsp").forward(req, resp);
			 //�ض���
			 //resp.sendRedirect("/day07/userUU/kyProjectUU/mainUU/mainUU.jsp");
			 
		}
	}
	
	//����ɾ���˺Ź���
	private int deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ����jsp������uid,��ǿ�ƽ�String����תΪint����
		int uid = Integer.parseInt(req.getParameter("uid"));
		System.out.println("��ȡ��ɾ����Ŀpid��" + uid);
		//����serviceɾ������
		us.deleteUser(uid);
		// �ص�showUserAA.jspҳ��
		List<User> showAllUser = us.userShowService();
		if (showAllUser != null) {
			//����ѯ�����û����ݴ洢��request����
			req.setAttribute("showAllUser", showAllUser);
			
			//����ת��
			//req.getRequestDispatcher("userUU/mainUU/mainUU.jsp").forward(req, resp);
			req.getRequestDispatcher("adminAA/userAA/showUserAA.jsp").forward(req, resp);
			 //�ض���
			 //resp.sendRedirect("/day07/userUU/kyProjectUU/mainUU/mainUU.jsp");
			 
		}
		return 0;
	}
	
	//�޸ı�������
	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//��ȡ��ʦ������
		String newPwd = req.getParameter("newPwd");
		//��ȡ����Ա������
		String newadminPwd = req.getParameter("newadminPwd");
		
		//��������
			//����service����
			String oper = req.getParameter("oper");
			if ("alterpwd".equals(oper)) {
				int uid = Integer.parseInt(req.getParameter("uid"));
				System.out.println("���������޸Ľ�ʦ��������");
				System.out.println("��Ϣ"+uid+newadminPwd);
				//���ý�ʦ�����޸ģ����룩����
				int index = us.userChangePwdService(uid,newPwd);
				if(index > 0) {
					//��ȡsession����
					HttpSession session2=req.getSession();
					session2.setAttribute("altsuccess", "��ʦ�����޸ĳɹ�");
					resp.sendRedirect("/day07/login.jsp");
				}
			}else if ("alteradminpwd".equals(oper)) {
				int aid = Integer.parseInt(req.getParameter("aid"));
				System.out.println("���������޸Ĺ���Ա��������");
				System.out.println("��Ϣ"+aid+newadminPwd);
				//���ù���Ա�����޸ģ����룩����
				int index1 = us.adminChangePwdService(aid,newadminPwd);
				if(index1 > 0) {
					//��ȡsession����
					HttpSession session2=req.getSession();
					session2.setAttribute("altsuccess", "����Ա�����޸ĳɹ�");
					resp.sendRedirect("/day07/login.jsp");
				}
			}
		
	}
		
}
