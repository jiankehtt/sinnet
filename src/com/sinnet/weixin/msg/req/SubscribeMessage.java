package com.sinnet.weixin.msg.req;

public class SubscribeMessage extends ReqBaseMessage {
	private String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
}
