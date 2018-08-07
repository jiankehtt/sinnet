package com.sinnet.action.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.sinnet.base.BaseWeChatAction;
import com.sinnet.service.UserService;
import com.sinnet.utils.StringUtil;


@Scope("prototype")
public class CourseAction extends BaseWeChatAction {
	private static final long serialVersionUID = -4619421920808930109L;
     

	@Autowired
	UserService userService;
	
	
	public String index() {
		super.initWechatData();
		if (user==null||StringUtil.isEmpty(user.getUsername())) {
			return "toError";
		}
		
		return "list";
	}
	
	
}
