package com.sinnet.weixin.job;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sinnet.weixin.msg.pojo.AccessToken;
import com.sinnet.weixin.utils.WeiXinUtil;

@Component
public class WeChatTokenJob {

//	public static String appid = "wx223d9d45aa16b9b3";
//	public static String appsecret = "bfa99c98cf6d28d0e2f6124b9aef4e08";
//	public static String myusername = "gh_a2c2baa649c7";
	public static String appid = "wx91df91d669ed9356";
	public static String appsecret = "60b13ecce22b9b678a5bd9da9d9390a8";
	public static String myusername = "gh_c4024bdafe55";

	// accsstoken
	public static AccessToken token;

	@Scheduled(cron = "0 0 0/1 * * *")
	public void reloadToken() {
		Logger.getLogger(WeChatTokenJob.class).error("reloadToken ------------------  ");
		token = WeiXinUtil.getAccessToken(appid, appsecret);
	}

}
