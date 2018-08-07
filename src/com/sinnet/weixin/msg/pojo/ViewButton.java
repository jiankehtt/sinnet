package com.sinnet.weixin.msg.pojo;

public class ViewButton extends Button {
	private String type;
	private String url;
	
	public ViewButton(String name,String url,String type){
		this.name = name;
		this.type = type;
		this.url = url;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
