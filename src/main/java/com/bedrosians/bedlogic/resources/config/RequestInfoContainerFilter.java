package com.bedrosians.bedlogic.resources.config;

import java.security.Principal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bedrosians.bedlogic.util.RequestInfoUtil;

@Provider
@Component
@ApplicationPath("resources")
public class RequestInfoContainerFilter implements ContainerRequestFilter {

	    @Autowired
	    private RequestInfoUtil currentUser;
		@Override
		public void filter(ContainerRequestContext containerRequestContext) {
			AtomicLong id = (AtomicLong)containerRequestContext.getProperty("requestId");
			if(id == null) {
		       Random random = new Random(System.currentTimeMillis());
		       id = new AtomicLong(Math.abs(random.nextLong()));
		       //containerRequestContext.setProperty("request_id", id);
		    } 
			currentUser.setRequestId(id);
			SecurityContext securityContext = containerRequestContext.getSecurityContext();
			Principal user  = securityContext.getUserPrincipal();
			//containerRequestContext.setProperty("current_user_name", user.getName());	
			if(user != null) {
  			   currentUser.setUserName(user.getName());
			   //System.out.println("user:" + user.getName());
			}
		}
}
