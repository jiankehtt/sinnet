package com.sinnet.database.model;

public class Feedback {
    private String feedId;

    private String feedContent;

    private String userGuid;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId == null ? null : feedId.trim();
    }

    public String getFeedContent() {
        return feedContent;
    }

    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent == null ? null : feedContent.trim();
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid == null ? null : userGuid.trim();
    }
}