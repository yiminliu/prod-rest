package com.bedrosians.bedlogic.exception;

public class BedDAOException extends Exception
{
    public BedDAOException() { super(); }
    public BedDAOException(String message) { super(message); }
    public BedDAOException(String message, Throwable cause) { super(message, cause); }
    public BedDAOException(Throwable cause) { super(cause); }
}
