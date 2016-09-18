package com.student.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.alibaba.fastjson.JSON;
import com.student.DBDao.AdminDao;
import com.student.DBDaoImp.AdminDBDaoImp;
import com.student.entity.AdminBean;
import com.student.entity.PageBean;
import com.student.entity.StudentBean;
import com.student.util.Contains;
import com.student.util.Json;
import com.student.util.StringUtil;

public class AdminServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDBDaoImp();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		String method = req.getParameter(Contains.METHOD);
		req.setCharacterEncoding("UTF-8");
		reps.setCharacterEncoding("UTF-8");
		switch (method) {
		case "login":
			login(req, reps);

			break;
		case "delete":
			delete(req, reps);
			break;
		case "regStudent":
			addOrUpdate(req, reps);

			break;
		case "chkuser":
			chkuser(req, reps);

			break;
		case "chkuserReg":
			chkuserReg(req, reps);

			break;
		case "tolist":
			tolist(req, reps);
			break;
		case "toupdate":
			toupdate(req, reps);

		default:
			break;
		}

	}

	private void toupdate(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		String updateId = req.getParameter("id");
		ObjectId _id=new ObjectId(updateId);
		AdminBean updatebean = new AdminBean();
		if (updateId != null || !updateId.equals("")) {
			updatebean = adminDao.getAdminbyId(_id);
		}
		req.setAttribute("updatebean", updatebean);
		req.getRequestDispatcher("addAdmin.jsp").forward(req, reps);
		
		
		
		
	}

	private void delete(HttpServletRequest req, HttpServletResponse reps) throws IOException {
		String id = req.getParameter("id");
		adminDao.deleteAdmin(new ObjectId(id));
		reps.sendRedirect("AdminServlet?method=tolist");

	}

	private void tolist(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {

		int currentPage = StringUtil.StringToInt(req.getParameter("currentPage"));
		int pageSize = adminDao.getcount();
		PageBean pageBean = new PageBean(currentPage, pageSize, Contains.PAGE_SIZE_1);
		List<AdminBean> adminBeans = adminDao.getListByPage(currentPage * Contains.PAGE_SIZE_1, Contains.PAGE_SIZE_1);
		pageBean.setAnd(true);
		pageBean.setPrefixUrl(req.getContextPath() + "/admin/AdminServlet?method=tolist");
		req.setAttribute("adminBeans", adminBeans);
		req.setAttribute("pageBean", pageBean);
     	req.getRequestDispatcher("/admin/listadmin.jsp").forward(req, reps);
		//reps.sendRedirect("listadmin.jsp");
	}

	/**
	 * 添加或者修改管理员
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */

	private void addOrUpdate(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("updateId");
		String account = req.getParameter(Contains.USER_ACCOUNT);
		String username = req.getParameter("username");
		String password = req.getParameter(Contains.PASS_WORD);
		AdminBean adminBean=null;
		// 当ID为空时添加新用户
		String status = "";
		if (id.equals("") || id == null) {
		adminBean = new AdminBean(username, account, password, 0);
			if (adminDao.addAdmin(adminBean)) {
				status = "1";
			} else {
				status = "2";
			}
		} else {
			 adminBean = new AdminBean(username, account, password, 0,new ObjectId(id));
			if(adminDao.updateAdmin(adminBean)){
				status = "3";
			}else {
				status = "4";
			}
		}
		reps.sendRedirect("addAdmin.jsp?status=" + status + "");
	}

	/**
	 * ajax 注册新管理员前的检测
	 * 
	 * @param req
	 * @param reps
	 */

	private void chkuserReg(HttpServletRequest req, HttpServletResponse reps) {
		String account = req.getParameter(Contains.USER_ACCOUNT);

		Json json = new Json();
		boolean flag = adminDao.chkuser(account);
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

	public void chkuser(HttpServletRequest req, HttpServletResponse reps) {
		String tel = req.getParameter(Contains.USER_ACCOUNT);
		Json json = new Json();
		if (adminDao.chkuser(tel)) {
			json.setMsg("用户名不存在！");
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
	 * 管理员登录
	 * 
	 * @param req
	 * @param reps
	 * @throws IOException
	 * @throws ServletException
	 */

	public void login(HttpServletRequest req, HttpServletResponse reps) throws IOException, ServletException {

		String account = req.getParameter(Contains.USER_ACCOUNT);
		String password = req.getParameter(Contains.PASS_WORD);
		AdminBean adminBean = new AdminBean();
		adminBean.setTelphone(account);
		adminBean.setPassword(password);
		adminBean = adminDao.login(adminBean);
		if (adminBean != null) {
			try {
				req.getSession().setAttribute(Contains.ADMIN_BEAN, adminBean);
				System.out.println(req.getSession().getAttribute("adminBean"));
				req.getRequestDispatcher("/admin/adminindex.jsp").forward(req, reps);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			req.setAttribute("status", "1");
			req.getRequestDispatcher("/admin/adminlogin.jsp").forward(req, reps);
		}

	}

}
