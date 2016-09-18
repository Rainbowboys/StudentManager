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
			// ������һҳ����ҳ
			if (pageBean.getCurrentPage() <= 0) {
				stf.append("<li class='disabled'><a>��ҳ</a></li>");
				stf.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
			} else {
				stf.append("<li><a href='").append(pageBean.getPrefixUrl())
						.append("' aria-label='Previous'><span aria-hidden='true'>��ҳ</span></a></li>");
				stf.append("<li><a href='").append(pageBean.getPrefixUrl()).append(pageBean.isAnd() ? "&" : "")
						.append("currentPage=").append(pageBean.getCurrentPage() - 1).append("'>��һҳ</a></li>");
			}

			// ������һҳ��βҳ
			if (pageBean.getCurrentPage() >= (pageBean.getPageCount() - 1)) {
				stf.append("<li class='disabled'><a>��һҳ</a></li>");
				stf.append("<li class='disabled'><a>βҳ</a></li>");
			} else {
				stf.append("<li><a href='").append(pageBean.getPrefixUrl()).append(pageBean.isAnd() ? "&" : "")
						.append("currentPage=").append(pageBean.getCurrentPage() + 1).append("'>��һҳ</a></li>");
				stf.append("<li><a href='").append(pageBean.getPrefixUrl()).append(pageBean.isAnd() ? "&" : "")
						.append("currentPage=").append(pageBean.getPageCount() - 1)
						.append("' aria-label='Previous'><span aria-hidden='true'>βҳ</span></a></li>");
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
