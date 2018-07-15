package com.sinnet.action.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.sinnet.base.AjaxBaseAction;
import com.sinnet.database.model.Feedback;
import com.sinnet.service.FeedbackService;
import com.sinnet.utils.RandomGUID;


@Scope("prototype")
public class AjaxFeedbackAction extends AjaxBaseAction {

	private static final long serialVersionUID = -3445173792823410267L;
	private String feedContent;
	
	@Autowired
	FeedbackService feedbackService;

	
	public String submit() {
		Feedback feedback = new Feedback();
		feedback.setFeedId(RandomGUID.getGUID());
		feedback.setFeedContent(feedContent);
		feedbackService.saveFeedBack(feedback);
		
		return BACK_DATA;
	}



	public String getFeedContent() {
		return feedContent;
	}



	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}
	
	
	
	
}
