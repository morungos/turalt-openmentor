package com.turalt.openmentor.response;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.turalt.openmentor.dto.User;

public class AbstractResponse {

	private URL serviceUrl;
	private User user;
	
	protected AbstractResponse() {}
	
	@JsonProperty
	public URL getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(URL serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	@JsonProperty
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
