package com.sinnet.base;

import com.opensymphony.xwork2.ActionContext;
import com.sinnet.database.model.User;


public class BaseWeChatAction extends BaseAction {
	private static final long serialVersionUID = -2651399573723937298L;

	protected String state; // 请求的状态码
	protected String code;

	protected String userId;
	



	public String getUserId() {
		return userId;
	}
	

	protected void initWechatData() {
		user = (User) ActionContext.getContext().getSession()
				.get(FRONT_USERKEY);
//		user = userService.getByOpenId("oSOiu0eKpPsM6pLlTIcB3MIdXTqs");
		if (user == null) {
			user = userService.loadFromWeChatAndUpdateUser(code);
			ActionContext.getContext().getSession().put(FRONT_USERKEY, user);
		}else{
			user = userService.getByOpenId(user.getWechatOpenid());
			ActionContext.getContext().getSession().put(FRONT_USERKEY, user);
		}
	
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	
	

}
