package com.sinnet.weixin.msg.pojo;

public class PageAccessToken {
	private String accessToken;
	private String expiresIn;
	private String refreshToken;
	private String openid;
	private String scope;
	private String uinionid;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUinionid() {
		return uinionid;
	}
	public void setUinionid(String uinionid) {
		this.uinionid = uinionid;
	}
}
