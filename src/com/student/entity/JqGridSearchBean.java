package com.student.entity;

import java.util.List;

/**
 * JGrid 查询的Bean
 * 
 * @author Rainbow
 *
 */
public class JqGridSearchBean {
	//
	private String groupOp; // 多字段查询时分组类型，主要是AND或者OR
	// private List rules; // 多字段查询时候，查询条件的集合
	private RuleBean[] rules;// 多字段查询时候，查询条件的集合
	private int page; // 当前第几页
	private int rows; // 每页显示多少条数据
	private String sidx; // 排序字段
	private String sord; // 排序类型 ASC或者DESC
	private boolean _search; // 是否是查询 true 或者 false
	private String nd; // 暂时不清楚啥用的

	private String searchField; // 单字段查询的时候，查询字段名称
	private String searchString; // 单字段查询的时候，查询字段的值
	private String searchOper; // 单字段查询的时候，查询的操作

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