package com.sinnet.database.dao;

import com.sinnet.database.model.Feedback;

public interface FeedbackMapper {
    int deleteByPrimaryKey(String feedId);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(String feedId);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);
}