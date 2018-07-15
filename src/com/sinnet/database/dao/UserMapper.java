package com.sinnet.database.dao;

import com.sinnet.base.BaseMapper;
import com.sinnet.database.model.User;

public interface UserMapper extends BaseMapper<User, String> {

	User selectByOpenId(String openid);
	
    
}