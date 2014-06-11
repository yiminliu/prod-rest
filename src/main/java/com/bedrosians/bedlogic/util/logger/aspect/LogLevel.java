package com.bedrosians.bedlogic.util.logger.aspect;

public enum LogLevel {
  
	TRACE("TRACE"), 
	DEBUG("DEBUG"),
	INFO("INFO"), 
	WARN("WARN"),
	ERROR("ERROR"),
	FATAL("FATAL");	
  
  public final String name;

  private LogLevel(String name) {
		this.name = name;
  }

  public String getName() {
		return name;
  }
    
}
