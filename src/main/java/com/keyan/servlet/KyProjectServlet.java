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
	
	
	//��ȡservice�����
	KyProjectService kyp = new KyProjectServiceImpl();
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//������������ʽ
		req.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
		resp.setContentType("text/html;charset=utf-8");
		//��ȡ������
		String oper = req.getParameter("oper");
		if("showProject".equals(oper)) {
			//���ò鿴������Ŀ��Ϣ����
			showProject(req,resp);
		}else if ("addProject".equals(oper)) {
			//����������Ŀ����
			addProject(req,resp);
		}else if ("pendingProject".equals(oper)) {
			//���ò鿴��������Ŀ����
			pendingProject(req,resp);
		}else if ("showProjectsearchadmin".equals(oper) || "showProjectsearchuser".equals(oper) || "pendingsearch".equals(oper)) {
			//����������Ŀ����
			searchProject(req,resp);
		}else if ("adopt".equals(oper) || "fail".equals(oper)) {
			System.out.println("������������servlet");
			//����������Ŀ����
			approvalProject(req,resp);
		}else if ("deleteProject".equals(oper)) {
			//����ɾ����Ŀ����
			deleteProject(req,resp);
		}else if ("modifyProject1".equals(oper)) {
			//�����޸���Ŀ����1
			modifyProject1(req,resp);
		}else if ("modifyProject2".equals(oper)) {
			//�����޸���Ŀ����2
			modifyProject2(req,resp);
		}else if ("ajax".equals(oper)) {
			//����ajaxProject
			System.out.println("��������servlet�е�ajax�ж�");
			ajaxProject(req,resp);
		}
			  
	}


	//�����޸���Ŀ����1
	private void modifyProject1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡsession����
		HttpSession session = req.getSession();
		
		//1 ��ȡǰ�δ�������Ҫ�޸ĵ���Ŀ��pid,��ǿ�ƽ�String����תΪint����
		int pid = Integer.parseInt(req.getParameter("pid"));
		//2 ����service��ѯ
		KyProject showModifyProject = kyp.showModifyProjectService(pid);
		
		if (showModifyProject != null) {
			//����Ŀ��Ϣ�洢��session�Ự��
			session.setAttribute("KyProject",showModifyProject);
			//����ת��
			req.getRequestDispatcher("adminAA/kyProjectAA/modifyProjectAA.jsp").forward(req, resp);
		}
	}
	//�����޸���Ŀ����2,��������޸�
	private void modifyProject2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�µ���Ŀ���������,�Լ����޸���Ŀ��pid
		String ptitle = req.getParameter("ptitle");
		String ptype = req.getParameter("ptype");
		int pid = Integer.parseInt(req.getParameter("pid"));
		//��������
			//����service����
		int index = kyp.ChangeKyProjectService(pid,ptitle,ptype);
		if(index > 0) {
			//����service����,��ѯ������Ŀ�����ص���ʾ������Ŀҳ��
			List<KyProject> showProject = kyp.projectShowService();
			
			if (showProject != null) {
				//����ѯ�����û����ݴ洢��request����
				req.setAttribute("showProject", showProject);
				//����ת��
				req.getRequestDispatcher("adminAA/mainAA/mainAA.jsp").forward(req, resp);
			}
			//����ת��
			//req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
			//resp.sendRedirect("/day07/adminAA/kyProjectAA/showProjectAA.jsp");
		}
		
	}
	
	//����ajaxProject
	private void ajaxProject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("��������servlet�е�ajaxִ�з���");
		
		//1.����service��ѯ
		List<Map<String, Object>> brands = kyp.ajaxprojectShowService(); 
		//System.out.println("��ѯ��������" + showProject);
		
		//2.������ת��Ϊjaon���� Ҳ�����л�,��
		/*
		 * resp.setContentType("application/json;charset=utf-8"); PrintWriter out =
		 * resp.getWriter(); out.write(JSONArray.fromObject(brands).toString());
		 * out.flush(); out.close();
		 */
		 
		 String jsonString = com.alibaba.fastjson.JSON.toJSONString(brands);
		 resp.getWriter().write(jsonString);
		
		 
		System.out.println("servletִ�����");
	}

	//��ʾ���п�����Ŀ��Ϣ
	private List<KyProject> showProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��������
			//����service����
			List<KyProject> showProject = kyp.projectShowService();
			
			if (showProject != null) {
				//����ѯ�����û����ݴ洢��request����
				req.setAttribute("showProject", showProject);
				
				String yonghu = req.getParameter("yonghu");
				System.out.println(yonghu);
				if ("admin".equals(yonghu)) {
					//����ת��
					req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
					return showProject;
				}else if("jiaoshi".equals(yonghu)){
					//����ת��
					req.getRequestDispatcher("userUU/kyProjectUU/showProjectUU.jsp").forward(req, resp);
					return showProject;
				}
				
			}
			return showProject;
	}

	//����������Ŀ����
	private void searchProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ptitle = req.getParameter("ptitle");
		System.out.println("�����������ع���,��������Ϊ��" + ptitle);
		//����service����
		List<KyProject> showProject = kyp.searchService(ptitle);
		List<KyProject> pendingProject = kyp.searchService(ptitle);
			if (showProject != null) {
				//����ѯ�����û����ݴ洢��request����
				req.setAttribute("showProject", showProject);
				req.setAttribute("pendingProject", pendingProject);
				
				//���ڶ��ҳ�湫��һ���������ܣ�����ҳ�����������ȥ
					//���ղ�����showProjectsearchadmin
					String oper = req.getParameter("oper");
				if("showProjectsearchadmin".equals(oper)) {
					//����ת����showProject.jsp
					req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
					return;
				}else if ("showProjectsearchuser".equals(oper)) {
					//����ת����pendingAA.jsp
					req.getRequestDispatcher("userUU/kyProjectUU/showProjectUU.jsp").forward(req, resp);
					return;
				}else if ("pendingsearch".equals(oper)) {
					//����ת����pendingAA.jsp
					req.getRequestDispatcher("adminAA/kyProjectAA/pendingAA.jsp").forward(req, resp);
					return;
				}
				
			}
	}
	
	//�鿴��������Ŀ����
	private int pendingProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��������
		//����service����
		List<KyProject> pendingProject = kyp.pendingProjectService();
		
		if (pendingProject != null) {
			//����ѯ�����û����ݴ洢��request����
			req.setAttribute("pendingProject", pendingProject);
			//����ת��
			req.getRequestDispatcher("adminAA/kyProjectAA/pendingAA.jsp").forward(req, resp);
		}
		return 0;
		
	}
	
	//������Ŀ����
	private int approvalProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//����jsp������pid,��ǿ�ƽ�String����תΪint����int
		System.out.println("����������������");
		int pstate = 1;
		int pid = Integer.parseInt(req.getParameter("pid"));
		String approva = req.getParameter("oper");
		if("adopt".equals(approva)) {
			//adopt��ʾͨ������pstate��ֵΪ2
			pstate = 2;
		}else if ("fail".equals(approva)) {
			//fail��ʾ��ͨ������pstate��ֵΪ3
			pstate = 3;
		}
		System.out.println("��ȡ��������Ŀpid��" + pid + "���޸ĺ������״̬" + pstate);
		
		//����service��������
		kyp.approvaProject(pid,pstate);
		
			//�ص�showProjectAA.jspҳ��
			List<KyProject> showProject = kyp.projectShowService();
			if (showProject != null) {
				//����ѯ�����û����ݴ洢��request����
				req.setAttribute("showProject", showProject);
				//����ת��
				req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
			}
			return 0;
	}
	
	//�����Ŀ
	private String addProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//����ǰ�δ��������ݣ��������װ��KyProject��
			String ptitle = req.getParameter("ptitle");
			String ptype = req.getParameter("ptype");
			//��ȡ��ǰ��¼�˻���������Ŀ�걨�ˣ�
				//��ȡsession����
				HttpSession session = req.getSession();
				
			User apname1 = (User)session.getAttribute("user");
			String apname = apname1.getUname();
			//���������ˣ�������ĿΪ ���ޣ�
			String exname = "����";
			//���ÿ�����Ŀ״̬��Ĭ��Ϊ��������
			int pstate = 1;
			//��ȡ��ǰϵͳʱ�䣨����Ŀ�걨ʱ�䣩
				//�������ڸ�ʽ
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String ptime = df.format(new Date());
		//��װΪ	KyProject ��	
		KyProject ky = new KyProject();
		ky.setApname(apname);
		ky.setExname(exname);
		ky.setPstate(pstate);
		ky.setPtitle(ptitle);
		ky.setPtype(ptype);
		ky.setPtime(ptime);
		//����service�е������Ŀ����
		kyp.addProject(ky);
		
		
		//�ص�showProjectUU.jspҳ��
		List<KyProject> showProject = kyp.projectShowService();
		if (showProject != null) {
			//����ѯ�����û����ݴ洢��request����
			req.setAttribute("showProject", showProject);
			
			//����ת�� req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
			//req.getRequestDispatcher("userUU/mainUU/mainUU.jsp").forward(req, resp);
			req.getRequestDispatcher("userUU/kyProjectUU/showProjectUU.jsp").forward(req, resp);
			 //�ض���
			 //resp.sendRedirect("/day07/userUU/kyProjectUU/mainUU/mainUU.jsp");
			 
		}
		return null;
	}
	
	//ɾ����Ŀ
	private int deleteProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//����jsp������pid,��ǿ�ƽ�String����תΪint����
		int pid = Integer.parseInt(req.getParameter("pid"));
		System.out.println("��ȡ��ɾ����Ŀpid��" + pid);
		//����serviceɾ������
		kyp.deleteProject(pid);
			//�ص�showProject.jspҳ��
			List<KyProject> showProject = kyp.projectShowService();
			if (showProject != null) {
				//����ѯ�����û����ݴ洢��request����
				req.setAttribute("showProject", showProject);
				//����ת��
				req.getRequestDispatcher("adminAA/kyProjectAA/showProjectAA.jsp").forward(req, resp);
			}
		return 0;
	}
	
	
	
}
