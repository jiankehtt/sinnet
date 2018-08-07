package com.sinnet.weixin.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinnet.weixin.job.WeChatTokenJob;
import com.sinnet.weixin.msg.pojo.AccessToken;
import com.sinnet.weixin.msg.pojo.Button;
import com.sinnet.weixin.msg.pojo.Menu;
import com.sinnet.weixin.msg.pojo.ViewButton;


public class MenuManager {
	private static Logger logger = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String args[]) {
		AccessToken accessToken = WeiXinUtil.getAccessToken(
				WeChatTokenJob.appid, WeChatTokenJob.appsecret);

		if (null != accessToken) {
			int result = WeiXinUtil.createMenu(getMenu(), accessToken
					.getToken());

			if (0 == result) {
				logger.info("创建菜单成功");
			} else {
				logger.info("创建菜单失败  errcode:{}", result);
			}

		}
	}

	/**
	 * 创建menu对象
	 * 
	 * @return
	 */
	private static Menu getMenu() {

		ViewButton  button1 = new ViewButton("培训课程"
				,contact("http://www.hushengyou.com/sinnet/front/course-index.do")
				,"view");
		
		ViewButton  button2 = new ViewButton("培训反馈"
				,contact("http://www.hushengyou.com/sinnet/front/feedback-index.do")
				,"view");
		

		ViewButton  button3 = new ViewButton("注册申请"
				,contact("http://www.hushengyou.com/sinnet/front/user-index.do")
				,"view");

		Menu menu = new Menu();
		menu.setButton(new Button[] { button1,button2,button3});

		return menu;
	}
	
	private static String contact(String url){
		String oahthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
			+ WeChatTokenJob.appid + "&redirect_uri=";
		String params = "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";

		try {
			return oahthUrl+URLEncoder.encode(url,"utf-8")+params;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
}
