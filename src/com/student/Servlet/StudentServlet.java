package com.student.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.student.DBDao.StudentDao;
import com.student.DBDaoImp.StudentDaoImp;
import com.student.entity.JqGridSearchBean;
import com.student.entity.PageBean;
import com.student.entity.RuleBean;
import com.student.entity.StudentBean;
import com.student.util.Contains;
import com.student.util.Json;
import com.student.util.StringUtil;
import com.student.util.StudentJson;

public class StudentServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao = new StudentDaoImp();

	/**
	 * ������������
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		String oper = req.getParameter("oper");

		if (method != null && method.equals("login")) {
			login(req, reps);

		} else if (method != null && method.equals("chkuser")) {
			chkuser(req, reps);

		} else if (method != null && method.equals("chkuserReg")) {
			chkuserReg(req, reps);
		} else if (method != null && method.equals("regStudent")) {

			regStudent(req, reps);
		} else if (method != null && method.equals("logout")) {
			logout(req, reps);
		} else if (method != null && method.equals("tolist")) {
			tolist(req, reps);
		} else if (method != null && method.equals("toupdate")) {
			toupdate(req, reps);
		} else if (method != null && method.equals("delete")) {
			delete(req, reps);
		} else if (method != null && method.equals("ajaxlist")) {
			ajaxlist(req, reps);
		} else if (oper != null && oper.equals("add")) {
			regStudent(req, reps);
		} else if (oper != null && oper.equals("del")) {
			delete(req, reps);
		} else if (oper != null && oper.equals("edit")) {
			regStudent(req, reps);
		}

	}

	/**
	 * ajax �첽������س�ʼ������ ȫ������
	 * 
	 * @param req
	 * @param reps
	 * @throws IOException
	 */

	private void ajaxlist(HttpServletRequest req, HttpServletResponse reps) throws IOException {
		int row = StringUtil.StringToInt(req.getParameter("rows"));
		int page = StringUtil.StringToInt(req.getParameter("page"));
		int total = 0;
		boolean _search = Boolean.valueOf(req.getParameter("_search")).booleanValue();
		String filters = req.getParameter("filters");
		int records = 0;
		List<StudentBean> studentBeans = null;
		Map<String,StudentBean> studentBeanmap = null;
		/**
		 * ��������ѯ ���ֶβ�ѯ
		 */
		if (_search && filters != null) {
			JqGridSearchBean searchBean = JSON.parseObject(filters, JqGridSearchBean.class);
			records = studentDao.getcountByFilter(searchBean);
			if(records>0){
//				studentBeans = studentDao.getListByFiter(searchBean);
				studentBeanmap = studentDao.getMapByFiter(searchBean);
				studentBeans =new ArrayList<>(studentBeanmap.values());
			}
			//��ҳ��
			total = (records - 1) / row + 1;
			StudentJson json = new StudentJson(page, total, records, studentBeans);
			
			PrintWriter pw = reps.getWriter();
			pw.write(JSON.toJSONString(json));
			pw.flush();
			pw.close();
		} else {
			records = studentDao.getcount();
			total = (records - 1) / row + 1;
			studentBeans = studentDao.getListByPage((page - 1) * row, row);
			StudentJson json = new StudentJson(page, total, records, studentBeans);
			PrintWriter pw = reps.getWriter();
			pw.write(JSON.toJSONString(json));
			pw.flush();
			pw.close();
		}
	}

	/**
	 * ajax �첽ɾ����¼
	 * 
	 * @param req
	 * @param reps
	 * @throws IOException
	 */

	private void delete(HttpServletRequest req, HttpServletResponse reps) throws IOException {

		String id = req.getParameter("id");
		String[] ids = StringUtil.splitString(id);
		studentDao.delete(ids);

	}

	// ����ǰ��׼�� ()
	private void toupdate(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		String updateId = req.getParameter("id");
		StudentBean updatebean = new StudentBean();
		if (updateId != null || !updateId.equals("")) {
			updatebean = studentDao.getStudentByTel(updateId);
		}
		req.setAttribute("updatebean", updatebean);
		req.getRequestDispatcher("../static/front/user/addUser.jsp").forward(req, reps);

	}

	/**
	 * ��û��ajax ֮ǰʹ�������������ҳ���ʼ������
	 * 
	 * @param req
	 * @param reps
	 * @throws ServletException
	 * @throws IOException
	 */

	private void tolist(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		int currentPage = StringUtil.StringToInt(req.getParameter("currentPage"));
		int pageSize = studentDao.getcount();
		PageBean pageBean = new PageBean(currentPage, pageSize, Contains.PAGE_SIZE_1);
		List<StudentBean> studentBeans = studentDao.getListByPage(currentPage * Contains.PAGE_SIZE_1,
				Contains.PAGE_SIZE_1);
		pageBean.setAnd(true);
		pageBean.setPrefixUrl(req.getContextPath() + "/student/StudentServlet?method=tolist");
		req.setAttribute("studentBeans", studentBeans);
		req.setAttribute("pageBean", pageBean);
		req.getRequestDispatcher("../static/front/user/listUsers.jsp").forward(req, reps);

	}

	/**
	 * ѧ����¼
	 * 
	 * @param studentBean
	 * @param studentDao
	 * @param req
	 * @param reps
	 * @throws IOException
	 * @throws ServletException
	 */
	public void login(HttpServletRequest req, HttpServletResponse reps) throws IOException, ServletException {
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		StudentBean studentBean = new StudentBean();
		studentBean.setTel(account);
		studentBean.setPassword(password);
		studentBean = studentDao.login(studentBean);
		if (studentBean != null) {
			try {
				req.getSession().setAttribute(Contains.USER_NAME, studentBean);
				req.getRequestDispatcher("index.jsp").forward(req, reps);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			reps.sendRedirect("login.jsp?status=1");
		}

	}

	/**
	 * Ajax����û��Ƿ����
	 * 
	 * @param studentBean
	 * @param studentDao
	 * @param req
	 * @param reps
	 */

	public void chkuserReg(HttpServletRequest req, HttpServletResponse reps) {
		String account = req.getParameter("account");
		StudentBean studentBean = new StudentBean();
		studentBean.setTel(account);
		Json json = new Json();
		boolean flag = studentDao.chkuser(studentBean);
		if (!flag) {
			json.setMsg("���ֻ����Ѿ�ע�ᣡ");
			json.setSuccess(true);
		}
		try {
			reps.setCharacterEncoding("utf-8");
			PrintWriter pw = reps.getWriter();
			pw.write(JSON.toJSONString(json));
			pw.flush();
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ajax����¼�û��Ƿ����
	 * 
	 * @param studentBean
	 * @param studentDao
	 * @param req
	 * @param reps
	 */

	public void chkuser(HttpServletRequest req, HttpServletResponse reps) {
		String account = req.getParameter("account");
		StudentBean studentBean = new StudentBean();
		studentBean.setTel(account);
		Json json = new Json();
		boolean flag = studentDao.chkuser(studentBean);
		if (flag) {
			json.setMsg("�û��������ڣ�");
			json.setSuccess(true);
		}
		try {
			reps.setCharacterEncoding("utf-8");
			PrintWriter pw = reps.getWriter();
			pw.write(JSON.toJSONString(json));
			pw.flush();
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ͨ�� JqGrid edit����ѧ������ ����
	 */

	/**
	 * ����Ա���ѧ��
	 * 
	 * @param studentBean
	 * @param studentDao
	 * @param req
	 * @param reps
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	public void regStudent(HttpServletRequest req, HttpServletResponse reps) throws IOException {
		String status = "0";
		String tel = req.getParameter("tel");
		String studentname = req.getParameter("username");
		String password = req.getParameter("password");
		String id = req.getParameter("id");

		StudentBean studentBean = new StudentBean(studentname, password, tel);

		// ����
		if (!id.equals("")) {
			studentBean.setId(id);
			if (studentDao.updateStudent(studentBean)) {
				// // �޸ĳɹ�
				// status = "2";
			}
		} else {
			boolean flag = studentDao.regStudent(studentBean);
			// if (flag) {
			// // ��ӳɹ�
			// status = "1";
			// }
		}
		// reps.sendRedirect(req.getContextPath() +
		// "/admin/addStudent.jsp?status=" + status + "");
	}

	/**
	 * ע����¼
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().removeAttribute(Contains.USER_NAME);
		resp.sendRedirect("../static/front/user/login.jsp");

	}
}
