package com.sinnet.base;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sinnet.constants.GlobalVars;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements GlobalVars {
	protected Logger logger = Logger.getLogger(getClass());

	
	
	private List<MapPair> useList;
	
	protected void initUseList(){
		useList = new ArrayList<MapPair>();
		useList.add(new MapPair(0,"��"));
		useList.add(new MapPair(1,"��"));
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

}
