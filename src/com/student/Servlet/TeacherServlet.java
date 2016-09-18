package com.student.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.alibaba.fastjson.JSON;
import com.student.DBDao.TeacherDao;
import com.student.DBDaoImp.TeacherDaoImp;
import com.student.entity.PageBean;
import com.student.entity.StudentBean;
import com.student.entity.TeacherBean;
import com.student.util.Contains;
import com.student.util.Json;
import com.student.util.StringUtil;
import com.student.util.StudentJson;

public class TeacherServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TeacherDao teacherDao = new TeacherDaoImp();

	@Override
	protected void service(HttpServletRequest rep, HttpServletResponse resp) throws ServletException, IOException {
		String method = rep.getParameter("method");
		String oper = rep.getParameter("oper");
		if(method!=null&&!method.equals("")){
			switch (method) {
			case "login":
				login(rep, resp);
				break;
			case "delete":
				delete(rep, resp);
				break;
			case "chkuser":
				chkuser(rep, resp);
				break;
			case "chkuserReg":
				chkuserReg(rep, resp);
				break;
			case "regTeacher":
				regTeacher(rep, resp);
				break;
			case "logout":
				logout(rep, resp);
				break;
			case "tolist":
				tolist(rep, resp);
				break;
			case "toupdate":
				toupdate(rep, resp);
				break;
			case "ajaxlist":
				ajaxlist(rep, resp);
				break;
			default:
				break;
			}
		}
		if(oper!=null&&!oper.equals("")){
			switch (oper) {
			case "add":
				regTeacher(rep, resp);
				break;
			case "del":
				delete(rep, resp);
				break;
			case "edit":
				regTeacher(rep, resp);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * ajax 异步请求加载初始化数据
	 * 
	 * @param req
	 * @param reps
	 * @throws IOException
	 */

	private void ajaxlist(HttpServletRequest req, HttpServletResponse reps) throws IOException {
		int row = StringUtil.StringToInt(req.getParameter("rows"));
		int page = StringUtil.StringToInt(req.getParameter("page"));
		int records = teacherDao.getcount();
		int total = (records - 1) / row + 1;
		List<TeacherBean> teacherBeans = teacherDao.getListByPage((page - 1) * row, row);
		StudentJson json = new StudentJson(page, total, records, teacherBeans);
		PrintWriter pw = reps.getWriter();
		pw.write(JSON.toJSONString(json));
		pw.flush();
		pw.close();
	}

	private void delete(HttpServletRequest req, HttpServletResponse reps) throws IOException {
		String id = req.getParameter("id");
		String[] ids = StringUtil.splitString(id);
		teacherDao.delete(ids);

	}

	// 更新前的准备
	private void toupdate(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		String updateId = req.getParameter("id");
		TeacherBean updatebean = new TeacherBean();
		if (updateId != null || !updateId.equals("")) {
			updatebean = teacherDao.getTeacherByTel(updateId);
		}
		req.setAttribute("updatebean", updatebean);
		req.getRequestDispatcher("../static/teacher/addUser.jsp").forward(req, reps);

	}

	private void tolist(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		int currentPage = StringUtil.StringToInt(req.getParameter("currentPage"));
		int pageSize = teacherDao.getcount();
		PageBean pageBean = new PageBean(currentPage, pageSize, Contains.PAGE_SIZE_1);
		List<TeacherBean> teacherBeans = teacherDao.getListByPage(currentPage * Contains.PAGE_SIZE_1,
				Contains.PAGE_SIZE_1);
		pageBean.setAnd(true);
		pageBean.setPrefixUrl(req.getContextPath() + "/teacher/TeacherServlet?method=tolist");
		req.setAttribute("teacherBeans", teacherBeans);
		req.setAttribute("pageBean", pageBean);
		req.getRequestDispatcher("../static/teacher/listUsers.jsp").forward(req, reps);

	}

	/**
	 * 学生登录
	 * 
	 * @param studentBean
	 * @param studentDao
	 * @param req
	 * @param reps
	 * @throws IOException
	 */
	public void login(HttpServletRequest req, HttpServletResponse reps) throws IOException {
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		TeacherBean teacherBean = new TeacherBean();
		teacherBean.setTelphone(account);
		teacherBean.setPassword(password);
		boolean flag = teacherDao.login(teacherBean);
		if (flag) {
			try {
				req.getSession().setAttribute(Contains.TEACHER_BEAN, teacherBean);
				reps.sendRedirect("success.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			reps.sendRedirect("../static/teacher/login.jsp?status=1");
		}

	}

	/**
	 * Ajax检测用户是否存在
	 * 
	 * @param studentBean
	 * @param studentDao
	 * @param req
	 * @param reps
	 */

	public void chkuserReg(HttpServletRequest req, HttpServletResponse reps) {
		String account = req.getParameter("account");
		TeacherBean teacherBean = new TeacherBean();
		teacherBean.setTelphone(account);
		Json json = new Json();
		boolean flag = teacherDao.chkuser(teacherBean);
		if (!flag) {
			json.setMsg("该手机号已经注册！");
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
	 * Ajax检测登录用户是否存在
	 * 
	 * @param studentBean
	 * @param studentDao
	 * @param req
	 * @param reps
	 */

	public void chkuser(HttpServletRequest req, HttpServletResponse reps) {
		String account = req.getParameter("account");
		TeacherBean teacherBean = new TeacherBean();
		teacherBean.setTelphone(account);
		Json json = new Json();
		boolean flag = teacherDao.chkuser(teacherBean);
		if (flag) {
			json.setMsg("用户名不存在！");
			json.setSuccess(true);
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
	}

	/**
	 * 管理员添加老师
	 * 
	 * @param studentBean
	 * @param studentDao
	 * @param req
	 * @param reps
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	public void regTeacher(HttpServletRequest req, HttpServletResponse reps) throws IOException {
		String status = "0";
		String account = req.getParameter("telphone");
		String teachername = req.getParameter("name");
		String password = req.getParameter("password");
		String id = req.getParameter("id");
//		String updateId = req.getParameter("updateId");
		TeacherBean teacherBean = new TeacherBean(teachername,account,password);
		// 更新
		if (id != null &&!id.equals("")) {
			teacherBean.setId(id);
		   teacherDao.updateTeacher(teacherBean);
		} else {
			 teacherDao.regTeacher(teacherBean);
		}
	reps.sendRedirect("../static/teacher/addUser.jsp?status=" + status + "");
	}

	/**
	 * 注销登录
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().removeAttribute(Contains.TEACHER_BEAN);
		resp.sendRedirect("../static/teacher/login.jsp");

	}

}
