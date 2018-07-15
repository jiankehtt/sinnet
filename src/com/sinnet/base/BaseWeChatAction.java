package com.sinnet.base;


public class BaseWeChatAction extends BaseAction {
	private static final long serialVersionUID = -2651399573723937298L;

	protected String state; // 请求的状态码
	protected String code;

	protected String userId;


	public String getUserId() {
		return userId;
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
