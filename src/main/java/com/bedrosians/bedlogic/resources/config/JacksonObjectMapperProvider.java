package com.bedrosians.bedlogic.resources.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

@ApplicationPath("resources")
@Provider
public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {
	
	final ObjectMapper defaultObjectMapper;
	final ObjectMapper customObjectMapper;
	
	public JacksonObjectMapperProvider() {
	   defaultObjectMapper = createDefaultMapper();
	   customObjectMapper = createCustomObjectMapper();
	}
	
	@Override
	public ObjectMapper getContext(final Class<?> type) {
	   if (type == Ims.class) {
	       return customObjectMapper;
	   } 
	   else {
	       return defaultObjectMapper;
	   }
	}
	
	private static ObjectMapper createCustomObjectMapper() {
	   final ObjectMapper result = new ObjectMapper();
	   result.enable(SerializationFeature.INDENT_OUTPUT);
	   //result.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
	   //result.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
	   result.setAnnotationIntrospector(createJaxbJacksonAnnotationIntrospector());
	   return result;
	}
	
	private static ObjectMapper createDefaultMapper() {
	   final ObjectMapper result = new ObjectMapper();
	   result.enable(SerializationFeature.INDENT_OUTPUT);
	   return result;
	}
	
	private static AnnotationIntrospector createJaxbJacksonAnnotationIntrospector() {
	   final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
	   final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
	   return AnnotationIntrospector.pair(jacksonIntrospector, jaxbIntrospector);
	}
	
}
