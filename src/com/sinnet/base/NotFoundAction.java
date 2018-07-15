package com.sinnet.base;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

@SuppressWarnings("serial")
public class NotFoundAction extends BaseAction {

	@Override
	public String execute() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		logger.warn("未找到Action,访问IP地址为" + req.getRemoteHost() + ",访问地址为"
				+ req.getRequestURL().toString());
		return super.execute();
	}
}
