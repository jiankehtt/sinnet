package com.sinnet.action.front;

import org.springframework.context.annotation.Scope;

import com.sinnet.base.BaseAction;


@Scope("prototype")
public class CourseAction extends BaseAction {
	private static final long serialVersionUID = -4619421920808930109L;

	public String index() {
		return "list";
	}
	
	
}
