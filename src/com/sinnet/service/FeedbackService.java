package com.sinnet.service;

import org.springframework.stereotype.Service;

import com.sinnet.base.BaseService;
import com.sinnet.database.dao.FeedbackMapper;
import com.sinnet.database.model.Feedback;

@Service
public class FeedbackService  extends BaseService<Feedback, String, FeedbackMapper>{

	public void saveFeedBack(Feedback feedback) {
		getMapper().insert(feedback);
	}


}
