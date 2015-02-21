package com.bedrosians.bedlogic.resources.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bedrosians.bedlogic.util.RequestInfo;

@Provider
@Component
@ApplicationPath("resources")
public class RequestInfoContainerFilter implements ContainerRequestFilter {

	    @Autowired
	    private RequestInfo requestInfo;
	    
		@Override
		public void filter(ContainerRequestContext containerRequestContext) {
			AtomicLong id = (AtomicLong)containerRequestContext.getProperty("request_id");
			if(id == null) {
		       Random random = new Random(System.currentTimeMillis());
		       id = new AtomicLong(Math.abs(random.nextLong()));
		       containerRequestContext.setProperty("request_id", id);
		    } 
			requestInfo.setRequestId(id);
			parseAuth(containerRequestContext);
		}
		
		private void parseAuth(ContainerRequestContext containerRequestContext) {
		   MultivaluedMap<String,String> headers = containerRequestContext.getHeaders();
		   List<String> authorizationList = (List<String>)headers.get("authorization");
	       if (authorizationList != null && !authorizationList.isEmpty()){
	           String[] authorizationHeader = authorizationList.get(0).split(" ");
               if (authorizationHeader.length == 2 && SecurityContext.BASIC_AUTH.equalsIgnoreCase(authorizationHeader[0])){
                   Base64 decoder = new Base64();
                   byte[]      decodedBytes = decoder.decode(authorizationHeader[1].getBytes());
                   String      decodedString = new String(decodedBytes);
                   String[]    authInfoArray = decodedString.split(":");
                   requestInfo.setUserName(authInfoArray[0]);
       			   containerRequestContext.setProperty("current_user_name", authInfoArray[0]);	
               }
           }
		}    
}
