package com.bedrosians.bedlogic.resources;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bedrosians.bedlogic.models.Hello;

@Path("/hello")
public class HelloResource {    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Hello getHello() {
      Hello hello = new Hello();
      hello.setFirst("John");
      hello.setLast("Doe");
      return hello;
    }    
}
