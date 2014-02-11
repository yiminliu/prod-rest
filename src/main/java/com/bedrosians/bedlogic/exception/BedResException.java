package com.bedrosians.bedlogic.exception;

public class BedResException extends Exception
{
    public BedResException() { super(); }
    public BedResException(String message) { super(message); }
    public BedResException(String message, Throwable cause) { super(message, cause); }
    public BedResException(Throwable cause) { super(cause); }
}
