package com.student.entity;

public class PageBean {
	// ������
	private int totalCount;
	// ��ҳ��
	private int pageCount;
	// ��ǰҳ
	private int currentPage;
	// һҳ����������
	private int countPerPage;

	private String prefixUrl;
	// true��ʱ����& �����ǣ�
	private boolean isAnd;

	public PageBean(int totalCount, int pageCount, int currentPage, int countPerPage, String prefixUrl, boolean isAnd) {
		super();
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
		this.countPerPage = countPerPage;
		this.prefixUrl = prefixUrl;
		this.isAnd = isAnd;
	}

	public PageBean() {
		// TODO Auto-generated constructor stub
	}

	public PageBean(int currentPage, int totalCount, int countPerPage) {
		this.countPerPage = countPerPage;
		pageCount = (totalCount - 1) / countPerPage + 1;

		if (currentPage >= pageCount) {
			currentPage = pageCount - 1;
		}
		if (currentPage < 0) {
			currentPage = 0;
		}

		this.totalCount = totalCount;
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	public String getPrefixUrl() {
		return prefixUrl;
	}

	public void setPrefixUrl(String prefixUrl) {
		this.prefixUrl = prefixUrl;
	}

	public boolean isAnd() {
		return isAnd;
	}

	public void setAnd(boolean isAnd) {
		this.isAnd = isAnd;
	}

}
