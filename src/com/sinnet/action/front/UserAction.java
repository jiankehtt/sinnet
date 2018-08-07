package com.sinnet.action.front;

import org.springframework.context.annotation.Scope;

import com.sinnet.base.BaseWeChatAction;


@Scope("prototype")
public class UserAction extends BaseWeChatAction {

	private static final long serialVersionUID = -890186173908473400L;
	
	public String index() {
		super.initWechatData();
		if("".equals(user.getUsername())||null==user.getUsername()){
			return "toreg";
		}else{
			return "person";
		}
	}
	
	public String suggest(){
		return "tosuggest";
	}
	
	public String toSuccess(){
		return SUCCESS_STR;
	}
	

	
	
}
