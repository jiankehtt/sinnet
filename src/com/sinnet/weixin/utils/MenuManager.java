package com.sinnet.weixin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinnet.weixin.job.WeChatTokenJob;
import com.sinnet.weixin.msg.pojo.AccessToken;
import com.sinnet.weixin.msg.pojo.Button;
import com.sinnet.weixin.msg.pojo.ComplexButton;
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
		String oahthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ WeChatTokenJob.appid + "&redirect_uri=";
		String params = "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		String app_url0 = "";
		String app_url1 = "";
		String app_url2 = ""; 

		try {
			app_url0 = URLEncoder
					.encode(
							"http://101.200.159.209/sinnet/front/user-index.do",
							"utf-8");
			app_url1 = URLEncoder
					.encode(
							"http://101.200.159.209/sinnet/front/course-index.do",
							"utf-8");
			app_url2 = URLEncoder
					.encode(
							"http://101.200.159.209/sinnet/front/feedback-index.do",
							"utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("url编码错误 {}", e);
		}
		
		ComplexButton  button1 = new ComplexButton();
		button1.setName("热门活动");

		ViewButton btn0 = new ViewButton();
		btn0.setType("view");
		btn0.setName("Sum");
		btn0.setUrl(oahthUrl + app_url0 + params);
		button1.setSub_button(new Button[]{btn0});
		
		ComplexButton  button2 = new ComplexButton();
		button2.setName("AWS培训");
		
		ViewButton b21 = new ViewButton();
		b21.setType("view");
		b21.setName("培训反馈");
		b21.setUrl(oahthUrl + app_url2 + params);
		ViewButton b22 = new ViewButton();
		b22.setType("view");
		b22.setName("培训课程");
		b22.setUrl(oahthUrl + app_url1 + params);
		ViewButton b23 = new ViewButton();
		b23.setType("view");
		b23.setName("培训简介");
		b23.setUrl(oahthUrl + app_url1 + params);
		button2.setSub_button(new Button[]{b21,b22,b23});
		
		
		ViewButton btn2 = new ViewButton();
		btn2.setType("view");
		btn2.setName("注册申请");
		btn2.setUrl(oahthUrl + app_url0 + params);

		Menu menu = new Menu();
		
		menu.setButton(new Button[] { button1,button2,btn2});

		return menu;
	}
}
