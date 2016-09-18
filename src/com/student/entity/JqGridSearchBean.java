package com.student.entity;

import java.util.List;

/**
 * JGrid ��ѯ��Bean
 * 
 * @author Rainbow
 *
 */
public class JqGridSearchBean {
	//
	private String groupOp; // ���ֶβ�ѯʱ�������ͣ���Ҫ��AND����OR
	// private List rules; // ���ֶβ�ѯʱ�򣬲�ѯ�����ļ���
	private RuleBean[] rules;// ���ֶβ�ѯʱ�򣬲�ѯ�����ļ���
	private int page; // ��ǰ�ڼ�ҳ
	private int rows; // ÿҳ��ʾ����������
	private String sidx; // �����ֶ�
	private String sord; // �������� ASC����DESC
	private boolean _search; // �Ƿ��ǲ�ѯ true ���� false
	private String nd; // ��ʱ�����ɶ�õ�

	private String searchField; // ���ֶβ�ѯ��ʱ�򣬲�ѯ�ֶ�����
	private String searchString; // ���ֶβ�ѯ��ʱ�򣬲�ѯ�ֶε�ֵ
	private String searchOper; // ���ֶβ�ѯ��ʱ�򣬲�ѯ�Ĳ���

	public JqGridSearchBean() {
		super();
	}

	public JqGridSearchBean(RuleBean[] rules, int page, int rows) {
		super();
		this.rules = rules;
		this.page = page;
		this.rows = rows;
	}

	public JqGridSearchBean(String groupOp, RuleBean[] rules, boolean _search, String searchField) {
		super();
		this.groupOp = groupOp;
		this.rules = rules;
		this._search = _search;
		this.searchField = searchField;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSearchOper() {
		return searchOper;
	}

	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	public boolean is_search() {
		return _search;
	}

	public void set_search(boolean _search) {
		this._search = _search;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public RuleBean[] getRules() {
		return rules;
	}

	public void setRules(RuleBean[] rules) {
		this.rules = rules;
	}

}