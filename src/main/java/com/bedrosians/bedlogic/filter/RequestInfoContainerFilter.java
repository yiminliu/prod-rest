package com.bedrosians.bedlogic.filter;

import java.security.Principal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bedrosians.bedlogic.util.RequestInfoUtil;

@Component
public class RequestInfoContainerFilter implements ContainerRequestFilter {

	    @Autowired
	    private RequestInfoUtil currentUser;
		@Override
		public void filter(ContainerRequestContext containerRequestContext) {
			AtomicLong id = (AtomicLong)containerRequestContext.getProperty("requestId");
			if(id == null) {
		       Random random = new Random(System.currentTimeMillis());
		       id = new AtomicLong(random.nextLong());
		       //containerRequestContext.setProperty("request_id", id);
		    } 
			currentUser.setRequestId(id);
			SecurityContext securityContext = containerRequestContext.getSecurityContext();
			Principal user  = securityContext.getUserPrincipal();
			//containerRequestContext.setProperty("current_user_name", user.getName());	
			if(user != null)
			currentUser.setUserName(user.getName());
		}
}
