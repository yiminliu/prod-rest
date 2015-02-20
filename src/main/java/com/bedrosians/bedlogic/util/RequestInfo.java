package com.bedrosians.bedlogic.util;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class RequestInfo implements Serializable{
	private static final long serialVersionUID = 321397721787L;
	private String userName;
	private AtomicLong requestId;
	
	public RequestInfo(){}
	
	public RequestInfo(String userName, AtomicLong requestId) {
		super();
		this.userName = userName;
		this.requestId = requestId;
	}

	public synchronized String getUserName() {
		return userName;
	}

	public synchronized void setUserName(String userName) {
		this.userName = userName;
	}

	public synchronized AtomicLong getRequestId() {
		return requestId;
	}

	public synchronized void setRequestId(AtomicLong requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "User:" + userName + "<request_id:" + requestId + ">";
	}

}
