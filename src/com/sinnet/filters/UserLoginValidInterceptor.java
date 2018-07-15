package com.sinnet.filters;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sinnet.constants.GlobalVars;

@SuppressWarnings("serial")
public class UserLoginValidInterceptor implements Interceptor, GlobalVars{

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
//		if(action instanceof UserManagerAction){	// 如果是和登录相关的话不进行拦截
//			return invocation.invoke();
//		}
		
		Object obj = ActionContext.getContext().getSession().get(USERKEY);
		if(obj == null){	// 如果没有登录的话则直接跳转到首页面
			return Action.LOGIN;
		}
		return invocation.invoke();
	}

}
