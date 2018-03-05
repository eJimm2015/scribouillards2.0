package com.parisdescartes.scrib.events;

import org.springframework.context.ApplicationEvent;

import com.parisdescartes.scrib.entities.User;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private User user;
	private String appUrl;
	
	public OnRegistrationCompleteEvent(User user, String appUrl) {
		super(user);
		this.user = user;
		this.appUrl = appUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	
	
	

}
