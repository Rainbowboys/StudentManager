package com.unit.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.student.DBDao.TestDao;
import com.student.DBDaoImp.TestDaoImp;
import com.student.entity.TestBean;
import com.student.util.StringUtil;
import com.student.util.StudentJson;
import com.student.util.UpFileUtil;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TestDao testDao = new TestDaoImp();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		if (method != null) {
			switch (method) {
			case "ajaxlist":
				ajaxlist(req, resp);
				break;
			case "upfile":
				try {
					upfile(req, resp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case "ajaxForm":
				ajaxForm(req, resp);
				break;
			case "AjaxFormUpfile":
				try {
					AjaxFormUpfile(req, resp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 异步表单提交 不刷新页面 ajaxform
	 * 
	 * @param req
	 * @param resp
	 */

	private void ajaxForm(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			PrintWriter pw = resp.getWriter();
			pw.write(JSON.toJSONString(username + " " + password));
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 处理文件上传
	 * 
	 * @param req
	 * @param resp
	 * @throws Exception
	 */

	private void upfile(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		try {
			List<FileItem> fileItems = upload.parseRequest(req);
			for (FileItem fileItem : fileItems) {
				fileItem.getString("utf-8");
				// 处理非表单数据 文件上传
				if (!fileItem.isFormField()) {
					UpFileUtil.processFileUpload(fileItem);
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ajaxForm upfile
	 * 
	 * @param fileItem
	 * @throws Exception
	 */

	private void AjaxFormUpfile(HttpServletRequest req, HttpServletResponse reps) throws Exception {

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		List<FileItem> fileItems = upload.parseRequest(req);
		PrintWriter pw = reps.getWriter();
		boolean flag = false;
		String msg = "上传失败";
		for (FileItem fileItem : fileItems) {
			if (!fileItem.isFormField()) {
				flag = UpFileUtil.processFileUpload(fileItem);
			}
		}
		if (flag) {
			msg = "成功";
		}
		pw.write(JSON.toJSONString(msg));
		pw.flush();
		pw.close();

	}

	private void ajaxlist(HttpServletRequest req, HttpServletResponse reps) throws IOException {
		int row = StringUtil.StringToInt(req.getParameter("rows"));
		int page = StringUtil.StringToInt(req.getParameter("page"));
		int records = testDao.getcount();
		int total = (records - 1) / row + 1;
		List<TestBean> testBeans = testDao.getListByPage(row * (page - 1), row);
		StudentJson json = new StudentJson(page, total, records, testBeans);
		PrintWriter pw = reps.getWriter();
		pw.write(JSON.toJSONString(json));
		pw.flush();
		pw.close();
	}

}