package com.student.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.student.entity.PageBean;

public class PageTag extends SimpleTagSupport {
	private PageBean pageBean;
	private HttpServletRequest request;

	@Override
	public void doTag() throws JspException, IOException {
		StringBuffer stf = new StringBuffer();
		if (pageBean != null) {
			stf.append("<nav><ul  class='pagination'>");
			// 处理上一页和首页
			if (pageBean.getCurrentPage() <= 0) {
				stf.append("<li class='disabled'><a>首页</a></li>");
				stf.append("<li class='disabled'><a href='#'>上一页</a></li>");
			} else {
				stf.append("<li><a href='").append(pageBean.getPrefixUrl())
						.append("' aria-label='Previous'><span aria-hidden='true'>首页</span></a></li>");
				stf.append("<li><a href='").append(pageBean.getPrefixUrl()).append(pageBean.isAnd() ? "&" : "")
						.append("currentPage=").append(pageBean.getCurrentPage() - 1).append("'>上一页</a></li>");
			}

			// 处理下一页和尾页
			if (pageBean.getCurrentPage() >= (pageBean.getPageCount() - 1)) {
				stf.append("<li class='disabled'><a>下一页</a></li>");
				stf.append("<li class='disabled'><a>尾页</a></li>");
			} else {
				stf.append("<li><a href='").append(pageBean.getPrefixUrl()).append(pageBean.isAnd() ? "&" : "")
						.append("currentPage=").append(pageBean.getCurrentPage() + 1).append("'>下一页</a></li>");
				stf.append("<li><a href='").append(pageBean.getPrefixUrl()).append(pageBean.isAnd() ? "&" : "")
						.append("currentPage=").append(pageBean.getPageCount() - 1)
						.append("' aria-label='Previous'><span aria-hidden='true'>尾页</span></a></li>");
			}
			stf.append("<li><a href='#'>");
			stf.append("<span>");
			stf.append(pageBean.getCurrentPage() + 1);
			stf.append("/");
			stf.append(pageBean.getPageCount());
			stf.append("</span>");
			stf.append("</a>");
			stf.append("</li>");
			stf.append("</ul></nav>");

		}
		getJspContext().getOut().write(stf.toString());
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
