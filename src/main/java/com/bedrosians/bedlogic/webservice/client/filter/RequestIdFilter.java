package com.bedrosians.bedlogic.webservice.client.filter;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class RequestIdFilter implements ClientRequestFilter {
		 
	@Override
	public void filter(final ClientRequestContext reqCtx){
		Random random = new Random(System.currentTimeMillis());
	    AtomicLong id = new AtomicLong(Math.abs(random.nextLong()));
	    reqCtx.setProperty("request_id", id);
	}		
}
