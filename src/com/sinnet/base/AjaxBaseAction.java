package com.sinnet.base;

import java.util.List;

import com.sinnet.utils.StringUtils;

@SuppressWarnings("serial")
public abstract class AjaxBaseAction extends BaseAction{
	
	protected final static  String BACK_DATA = "backData" ;

	protected PageBean pageBean = new PageBean();
	
	protected BackData backData = new BackData();
	
	public BackData getBackData() {
		return backData;
	}

	public AjaxBaseAction(){
		super();
		pageBean.setOrderProperty(getDefaultOrderProperty());
	}
	
	public PageBean getPageBean() {
		return pageBean;
	}
	
	
	public PageBean getPageBean(String orderProp, boolean isAsc){
		if(pageBean != null && StringUtils.isEmpty(pageBean.getOrderProperty())){
			pageBean.setOrderProperty(orderProp);
			pageBean.setOrderDesc(isAsc ? "asc" : "desc");
		}
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getDefaultOrderProperty(){return "";};
	
	@SuppressWarnings("unchecked")
	public void setResultList(List list){
		if(getPageBean() != null)
			getPageBean().setList(list);
	}
	private void setStateWithMessage(String state,String message){
		if(getBackData()!=null){
			getBackData().setMessage(message);
			getBackData().setState(state);
		}
	}
	
	public void setErrorMessage(String message){
		setStateWithMessage(ERROR_STR, message);
	}
	
	public void setSucessMessage(String message){
		setStateWithMessage(SUCCESS_STR, message);
	}
}
