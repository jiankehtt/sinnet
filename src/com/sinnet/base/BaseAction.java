package com.sinnet.base;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.sinnet.constants.GlobalVars;
import com.sinnet.database.model.User;
import com.sinnet.service.UserService;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements GlobalVars {
	protected Logger logger = Logger.getLogger(getClass());

	public User user;
	
	protected String state; // 请求的状态码
	
	private List<MapPair> useList;
	@Autowired
	UserService userService;
	
	protected void initUseList(){
		useList = new ArrayList<MapPair>();
		useList.add(new MapPair(0,"是"));
		useList.add(new MapPair(1,"否"));
	}
	

	/**
	 * 
	 * 
	 * @param message
	 */
	protected void putJsonFail(String message) {
		String json = String.format(FAILMSG, message);
		putJsonData(json);
	}

	protected void putJsonSuccess() {
		putJsonData(SUCCESS);
	}
	
	protected void putJsonSuccessData(String message) {
		String json = String.format(SUCCESSMSG, message);
		putJsonData(json);
	}

	protected void putJsonData(String json) {
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
		} catch (IOException e) {
			logger.error(e);
		}
	}

	protected boolean isValid(String param) {
		if (param == null || "".equals(param))
			return false;
		return true;
	}
	
	public List<MapPair> getUseList() {
		return useList;
	}

	public void setUseList(List<MapPair> useList) {
		this.useList = useList;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
