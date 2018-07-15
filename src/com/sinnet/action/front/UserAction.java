package com.sinnet.action.front;

import org.springframework.context.annotation.Scope;

import com.sinnet.base.BaseAction;


@Scope("prototype")
public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -890186173908473400L;
	
	public String index() {
		return "toreg";
	}
	
	public String toSuccess(){
		return SUCCESS_STR;
	}
	

	
	
}
