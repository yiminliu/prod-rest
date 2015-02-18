package com.bedrosians.bedlogic.util;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class RequestInfoUtil implements Serializable{
	private static final long serialVersionUID = 321397721787L;
	private String userName;
	private AtomicLong requestId;
	
	public RequestInfoUtil(String userName, AtomicLong requestId) {
		super();
		this.userName = userName;
		this.requestId = requestId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public AtomicLong getRequestId() {
		return requestId;
	}

	public void setRequestId(AtomicLong requestId) {
		this.requestId = requestId;
	}
	
}
