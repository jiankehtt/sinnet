package com.sinnet.action.wechat;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.alibaba.fastjson.JSONObject;
import com.sinnet.base.BaseAction;
import com.sinnet.database.model.User;
import com.sinnet.service.UserService;
import com.sinnet.utils.SignatureUtils;
import com.sinnet.weixin.job.WeChatTokenJob;
import com.sinnet.weixin.msg.res.ResTextMessage;
import com.sinnet.weixin.utils.MessageUtil;


@Scope("prototype")
public class WechatAction extends BaseAction {

	private static final long serialVersionUID = 3024342501651860031L;
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	
	@Autowired
	private UserService userService;

	private static String TOKEN = "mytestWechatValidatorToken";

	public void handle() {
		if (signature != null && timestamp != null && nonce != null
				&& echostr != null) {
			logger.info("handle" + signature + " : " + timestamp + " : " + nonce
					+ " : " + echostr);
			if (SignatureUtils.checkSignature(TOKEN, signature, timestamp,
					nonce)) {
				logger.info("handle  echoStr=" + echostr);
				putJsonData(echostr);
				return;
			}
		}
		
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		Map<String, String> map = MessageUtil.parseXML(req);
		logger.info("map "+JSONObject.toJSONString(map));
		if(map != null){
			String msgType = map.get("MsgType");
			String openid = map.get("FromUserName");
			logger.info("msgType "+msgType+" openid "+openid);
			if(msgType.equals("event")){
				String event = map.get("Event");
				if( event.equals("subscribe" )){
					
					res.setContentType("text/xml;charset=UTF-8");
					res.setCharacterEncoding("UTF-8");
					
					ResTextMessage rtm = new ResTextMessage();
					rtm.setFromUserName(WeChatTokenJob.myusername);
					rtm.setToUserName(openid);
					rtm.setMsgType("text");
					rtm.setContent("欢迎");
					rtm.setCreateTime(System.currentTimeMillis());
					
					String textXml = MessageUtil.resTextMessageToXML(rtm);
					
					try {
						res.getWriter().write(textXml);
					} catch (IOException e) {
						logger.error("error in wechat ",e);
					}
					
					User user = userService.getByOpenId(openid);
					if(user==null){
						userService.saveUserByOpenId(openid);
						logger.error("attion_openid"+openid);
					}
					
				}else if(event.equals("unsubscribe")){
					logger.error("cancel_attion_openid"+openid);
				}else{
					res.setContentType("text/plain;charset=UTF-8");
					res.setCharacterEncoding("UTF-8");
					try {
						res.getWriter().write("success");
					} catch (IOException e) {
						logger.error("error in wechat ",e);
					}
				}
			}else{
				res.setContentType("text/plain;charset=UTF-8");
				res.setCharacterEncoding("UTF-8");
				try {
					res.getWriter().write("success");
				} catch (IOException e) {
					logger.error("error in wechat ",e);
				}
			}
		}

	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

}
