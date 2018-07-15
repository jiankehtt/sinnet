package com.sinnet.action.front;

import org.springframework.context.annotation.Scope;

import com.sinnet.base.BaseAction;


@Scope("prototype")
public class FeedbackAction extends BaseAction {
	private static final long serialVersionUID = -8944699448200894942L;

	public String index() {
		return "tofeedback";
	}
	
	public String toSuccess(){
		return SUCCESS_STR;
	}
	

	
	
}
