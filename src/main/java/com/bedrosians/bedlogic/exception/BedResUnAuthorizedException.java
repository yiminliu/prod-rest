package com.bedrosians.bedlogic.exception;

public class BedResUnAuthorizedException extends BedResException
{
    public BedResUnAuthorizedException() { super(); }
    public BedResUnAuthorizedException(String message) { super(message); }
    public BedResUnAuthorizedException(String message, Throwable cause) { super(message, cause); }
    public BedResUnAuthorizedException(Throwable cause) { super(cause); }
}
