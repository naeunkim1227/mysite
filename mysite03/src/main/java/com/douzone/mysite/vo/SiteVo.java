package com.douzone.mysite.vo;

public class SiteVo {
	private String title;
	private String welcomeMessage;
	private String profileURL;
	private String description;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	public String getProfileURL() {
		return profileURL;
	}
	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "SiteVo [title=" + title + ", welcomeMessage=" + welcomeMessage + ", profileURL=" + profileURL
				+ ", description=" + description + "]";
	}
	
}
