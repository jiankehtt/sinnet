package com.sinnet.database.dao;

import com.sinnet.database.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String guid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}