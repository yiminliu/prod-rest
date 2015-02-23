package com.bedrosians.bedlogic.webservice.client.filter;

import java.util.Random;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class RequestIdContainerFilter implements ContainerRequestFilter {

		@Override
		public void filter(ContainerRequestContext containerRequestContext) {
		    Random random = new Random(System.currentTimeMillis());
		    Long id = random.nextLong();
		    containerRequestContext.setProperty("requestId", id);
		}    

}
