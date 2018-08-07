package com.sinnet.action.front;

import org.springframework.context.annotation.Scope;

import com.sinnet.base.BaseWeChatAction;
import com.sinnet.utils.StringUtil;


@Scope("prototype")
public class FeedbackAction extends BaseWeChatAction {
	private static final long serialVersionUID = -8944699448200894942L;

	public String index() {
		super.initWechatData();
		if (user==null||StringUtil.isEmpty(user.getUsername())) {
			return "toError";
		}
		return "tofeedback";
	}
	
	public String toSuccess(){
		return SUCCESS_STR;
	}
	

	
	
}
