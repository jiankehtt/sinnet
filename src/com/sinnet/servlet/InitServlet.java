package com.sinnet.servlet;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinnet.weixin.job.WeChatTokenJob;
import com.sinnet.weixin.utils.WeiXinUtil;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = -7190583697068197243L;
	private static Logger logger = LoggerFactory.getLogger(InitServlet.class);
	
	public void init(){
		WeChatTokenJob.appid = getInitParameter("appid");
		WeChatTokenJob.appsecret = getInitParameter("appsecret");
		WeChatTokenJob.myusername = getInitParameter("myusername");
		
		logger.info("weixin api appid:{}", WeChatTokenJob.appid);
		logger.info("weixin api appsecret:{}", WeChatTokenJob.appsecret);
		logger.info("myusername:{} ", WeChatTokenJob.myusername);
		
		if("".equals(WeChatTokenJob.appid) || "".equals(WeChatTokenJob.appsecret)){
			logger.error("΢ appid is null or app secret is null");
		}else{
			logger.debug("load token");
			WeChatTokenJob.token = WeiXinUtil.getAccessToken(WeChatTokenJob.appid, WeChatTokenJob.appsecret);
		}
	}
}
