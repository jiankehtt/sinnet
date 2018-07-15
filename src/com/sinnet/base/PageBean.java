package com.sinnet.base;

import java.util.ArrayList;
import java.util.List;

import com.sinnet.constants.GlobalVars;


@SuppressWarnings("unchecked")
public class PageBean {
	private int pageSize = GlobalVars.PAGE_SIZE;
	private int pageNo = 1; 
	private int totalPage;
	private int totalRecorde; 
	private int prep; 
	private int nextp;

	private String orderProperty;
	private String orderDesc = "asc";
	private List list = new ArrayList();

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		if (this.getTotalRecorde() % this.getPageSize() == 0)
			this.totalPage = totalRecorde / pageSize;
		else
			this.totalPage = totalRecorde / pageSize + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecorde() {
		return totalRecorde;
	}

	public void setTotalRecorde(int totalRecorde) {
		this.totalRecorde = totalRecorde;
	}

	public int getPrep() {
		if (this.getPageNo() <= 1)
			this.setPrep(1);
		else
			this.setPrep(pageNo - 1);
		return prep;
	}

	public void setPrep(int prep) {
		this.prep = prep;
	}

	public int getNextp() {
		if (this.getPageNo() >= this.getTotalPage())
			this.setNextp(getTotalPage());
		else
			this.setNextp(pageNo + 1);
		return nextp;
	}

	public void setNextp(int nextp) {
		this.nextp = nextp;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	public String getOrderDesc() {
		if ("asc".equals(orderDesc) || "desc".equals(orderDesc))
			return orderDesc;
		else
			return "";
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

}
