package com.sinnet.service;

import org.springframework.stereotype.Service;

import com.sinnet.base.BaseService;
import com.sinnet.database.dao.UserMapper;
import com.sinnet.database.model.User;
import com.sinnet.utils.RandomGUID;
import com.sinnet.weixin.job.WeChatTokenJob;
import com.sinnet.weixin.msg.pojo.UserInfo;
import com.sinnet.weixin.utils.WeiXinUtil;

@Service
public class UserService extends BaseService<User, String, UserMapper> {

	public void saveOrUpdateUser(User user) {
		User u = getByOpenId(user.getWechatOpenid());
		if(u!=null){
			getMapper().updateByPrimaryKey(user);
		}else{
			getMapper().insert(user);
		}
	}
	
	public User getByOpenId(String openid) {
		return getMapper().selectByOpenId(openid);
	}

	public void saveUserByOpenId(String openid) {
		User user = new User();
		user.setGuid(RandomGUID.getGUID());
		user.setWechatOpenid(openid);
		getMapper().insert(user);

	}

	public User loadFromWeChatAndUpdateUser(String code) {
		UserInfo userInfo = WeiXinUtil.getUserInfo(code, WeChatTokenJob.appid,
				WeChatTokenJob.appsecret);
		User user = null;
		if (userInfo != null) {
			user = getByOpenId(userInfo.getOpenId());
			if (user == null) {
				User newUser = new User();
				newUser.setGuid(RandomGUID.getGUID());
				newUser.setWechatOpenid(userInfo.getOpenId());
				newUser.setWechatCity(userInfo.getCity());
				newUser.setWechatCountry(userInfo.getCountry());
				newUser.setWechatHeadimgurl(userInfo.getHeadimgUrl());
				newUser.setWechatNickname(userInfo.getNikeName());
				newUser.setWechatProvince(userInfo.getProvince());
				newUser.setWechatSex(Byte.parseByte(userInfo.getSex()));
				getMapper().insert(newUser);
				user = newUser;
			}else{
				user.setWechatOpenid(userInfo.getOpenId());
				user.setWechatCity(userInfo.getCity());
				user.setWechatCountry(userInfo.getCountry());
				user.setWechatHeadimgurl(userInfo.getHeadimgUrl());
				user.setWechatNickname(userInfo.getNikeName());
				user.setWechatProvince(userInfo.getProvince());
				getMapper().updateByPrimaryKey(user);
			}
		}
		if (userInfo == null) {
			logger.error("userInfo is null : " + code);
		}
		return user;
	}

}
