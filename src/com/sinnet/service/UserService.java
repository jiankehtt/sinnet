package com.sinnet.service;

import org.springframework.stereotype.Service;

import com.sinnet.base.BaseService;
import com.sinnet.database.dao.UserMapper;
import com.sinnet.database.model.User;
import com.sinnet.utils.RandomGUID;

@Service
public class UserService  extends BaseService<User, String, UserMapper>{

	public void saveUser(User user) {
		getMapper().insert(user);
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


}
