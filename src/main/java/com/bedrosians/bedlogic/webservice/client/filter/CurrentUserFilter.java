package com.bedrosians.bedlogic.webservice.client.filter;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class CurrentUserFilter implements ClientRequestFilter {

	    private String userName;
	    private String password;

	    public CurrentUserFilter(String userName) {
	        this.userName = userName;
	    }
	    
	    public CurrentUserFilter(String userName, String password) {
	        this.userName = userName;
	        this.password = password;
	    }

	    public void filter(ClientRequestContext requestContext) {
	        //MultivaluedMap<String, Object> headers = requestContext.getHeaders();
	        //final String basicAuthentication = getBasicAuthentication();
	        //headers.add("Authorization", basicAuthentication);
	        requestContext.setProperty("userName", userName);
	    }

	    //private String getBasicAuthentication() {
	    //   String token = this.userName + ":" + this.password;
	    //   try {
	    //       return "BASIC " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
	    //  } catch (UnsupportedEncodingException ex) {
	    //      throw new IllegalStateException("Cannot encode with UTF-8", ex);
	    // }
	    //}
}
