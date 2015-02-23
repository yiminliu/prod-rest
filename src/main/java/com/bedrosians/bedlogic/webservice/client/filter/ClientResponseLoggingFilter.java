package com.bedrosians.bedlogic.webservice.client.filter;

import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

public class ClientResponseLoggingFilter implements ClientResponseFilter {
		 
		@Override
		public void filter(final ClientRequestContext reqCtx, final ClientResponseContext resCtx){
			System.out.println("userName: " + reqCtx.getProperty("userName"));
			System.out.println("requestId: " + reqCtx.getProperty("requestId"));
			System.out.println("status: " + resCtx.getStatus());
			System.out.println("headers:");
			for (Entry<String, List<String>> header : resCtx.getHeaders().entrySet()) {
				System.out.print("\t" + header.getKey() + " :");
				for (String value : header.getValue()) {
					System.out.print(value + ", ");
				}
				System.out.print("\n");
			}	
		}

}
