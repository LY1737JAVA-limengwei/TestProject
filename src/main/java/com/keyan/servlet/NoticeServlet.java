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

	// ��ȡservice�����
	NoticeService noti = new NoticeServiceImpl();

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ������������ʽ
		req.setCharacterEncoding("utf-8");
		// ������Ӧ�����ʽ
		resp.setContentType("text/html;charset=utf-8");
		// ��ȡ������
		String oper = req.getParameter("oper");
		
		if ("showNotice".equals(oper)) {
			System.out.println("�������˲鿴����servlet");
			//���ò鿴���湦��
			showNotice(req, resp);
		} else if ("addNotice".equals(oper)) {
			System.out.println("����������ӹ���servlet");
			// �����������湦��
			addNotice(req, resp);
		} else if ("deleteNotice".equals(oper)) {
			// ����ɾ�����湦��
			System.out.println("��������ɾ������servlet");
			deleteNotice(req, resp);
		} else if ("showmodifyNotice".equals(oper)) {
			// �����޸Ĺ��湦��1
			System.out.println("���������޸Ĺ���1servlet");
			showmodifyNotice(req, resp);
		} else if ("modifyNotice".equals(oper)) {
			// �����޸Ĺ��湦��2
			System.out.println("���������޸Ĺ���2servlet");
			modifyNotice(req, resp);
		} else if ("searchNoticeadmin".equals(oper) || "searchNoticeteacher".equals(oper)) {
			System.out.println("����������������servlet");
			// �����������湦��
			searchNotice(req, resp);
		}

	}

	//�������湦��
	private void searchNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nitile = req.getParameter("nitile");
		System.out.println("�����������ع���,��������Ϊ��" + nitile);
		
		//����service����
		List<Notice> showNotice = noti.searchService(nitile);
			if (showNotice != null) {
				//����ѯ�����û����ݴ洢��request����
				req.setAttribute("showNotice", showNotice);
				
				//���ڶ��ҳ�湫��һ���������ܣ�����ҳ�����������ȥ
					//���ղ�����
					String oper = req.getParameter("oper"); 
					System.out.println("���յ��Ĳ�����" + oper);
					if("searchNoticeadmin".equals(oper)){ 
						//����ת����showNoticeAA.jsp
						req.getRequestDispatcher("adminAA/noticeAA/showNoticeAA.jsp").forward(req, resp);
						return; 
					}else if ("searchNoticeteacher".equals(oper)) {
						//����ת����showNoticeUU.jsp
						req.getRequestDispatcher("userUU/noticeUU/showNoticeUU.jsp").forward(req,resp);
						return; 
					}
				
			}
	}

	// ��ʾ���й�����Ϣ
	private List<Notice> showNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("��������servlet�еĲ鿴������Ϣ����");
		
		//����service����
		List<Notice> showNotice = noti.ShownoticeService();
		
		if (showNotice != null) {
			//����ѯ�����û����ݴ洢��request����
			req.setAttribute("showNotice", showNotice);
			
			String yonghu = req.getParameter("yonghu");
			if ("admin".equals(yonghu)) {
				//����ת��
				req.getRequestDispatcher("adminAA/noticeAA/showNoticeAA.jsp").forward(req, resp);
				return showNotice;
			}else if("jiaoshi".equals(yonghu)){
				//����ת��
				req.getRequestDispatcher("userUU/noticeUU/showNoticeUU.jsp").forward(req, resp);
				return showNotice;
			}
			
		}
		return showNotice;
	}

	// ��ӹ���
	private List<Notice> addNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ����ǰ�δ��������ݣ��������װ��Notice��
		String nitile = req.getParameter("nitile");//�������
		String ncontent = req.getParameter("ncontent");//��������
		// ��ȡ��ǰ��¼�˻����������淢���ˣ�
		// ��ȡsession����
		HttpSession session = req.getSession();
		Admin name1 = (Admin)session.getAttribute("admin");
		String ndepartid = name1.getaname();
		
		// ��ȡ��ǰϵͳʱ�䣨�����淢��ʱ�䣩
		// �������ڸ�ʽ
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String ntime = df.format(new Date());
		
		//��װΪ KyProject �� 
		Notice noc = new Notice(); 
		noc.setNitile(nitile);
		noc.setNcontent(ncontent);
		noc.setNtime(ntime);
		noc.setNdepartid(ndepartid);
		System.out.println("��װ�õ�����" + noc);
		// ����service�е������Ŀ����
		noti.addNotice(noc);
		
		//�ص�showNoticeAA.jspҳ��
		List<Notice> showNotice = noti.ShownoticeService();
		
		if (showNotice != null) {
			//����ѯ�����û����ݴ洢��request����
			req.setAttribute("showNotice", showNotice);
			//����ת��
			req.getRequestDispatcher("adminAA/mainAA/mainAA.jsp").forward(req, resp);
			//�ض���/notice?oper=showNotice
			//resp.sendRedirect("adminAA/mainAA/leftAA.jsp");
			return showNotice;
		}
		return showNotice;
	}

	// �����޸Ĺ��湦��1
	private void showmodifyNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡsession����
		HttpSession session = req.getSession();
		
		//1 ��ȡǰ�δ�������Ҫ�޸ĵ���Ŀ��id,��ǿ�ƽ�String����תΪint����
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("����һ�õ���id" + id);
		//2 ͨ��id����service��ѯ
		Notice showModifyNotice = noti.showModifyNoticeService(id);
		
		if (showModifyNotice != null) {
			//����Ŀ��Ϣ�洢��session�Ự��
			session.setAttribute("Notice",showModifyNotice);
			//����ת��
			req.getRequestDispatcher("adminAA/noticeAA/modifyNoticeAA.jsp").forward(req, resp);
		}
	}
	// �����޸Ĺ��湦��2
	private void modifyNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�µĹ�����������,�Լ����޸���Ŀ��id
		String nitile = req.getParameter("nitile");
		String ncontent = req.getParameter("ncontent");
		int id = Integer.parseInt(req.getParameter("id"));
		//��������
			//����service����
			int index = noti.ChangeNoticeService(id,nitile,ncontent);
			if(index > 0) {
			//����service����,��ѯ������Ŀ�����ص���ʾ������Ŀҳ��
				List<Notice> showNotice = noti.ShownoticeService();
			
			if (showNotice != null) {
				//����ѯ�����û����ݴ洢��request����
				req.setAttribute("showNotice", showNotice);
				//����ת��
				req.getRequestDispatcher("adminAA/mainAA/mainAA.jsp").forward(req, resp);
			}
		}
	}
	
	// ɾ����Ŀ
	private int deleteNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ����jsp������pid,��ǿ�ƽ�String����תΪint����
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("��ȡ��ɾ����Ŀpid��" + id);
		//����serviceɾ������
		noti.deleteNotice(id);
		// �ص�showNotice.jspҳ��
		List<Notice> showNotice = noti.ShownoticeService();
		if (showNotice != null) { 
			// ����ѯ�����û����ݴ洢��request����
			req.setAttribute("showNotice", showNotice); 
			// ����ת��
			req.getRequestDispatcher("adminAA/noticeAA/showNoticeAA.jsp").forward(req, resp); 
		}
		return 0;
	}

}
