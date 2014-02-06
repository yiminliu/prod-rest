package com.bedrosians.bedlogic.exception;

import com.bedrosians.bedlogic.exception.BedDAOException;

public class BedDAOUnAuthorizedException extends BedDAOException
{
    public BedDAOUnAuthorizedException() { super(); }
    public BedDAOUnAuthorizedException(String message) { super(message); }
    public BedDAOUnAuthorizedException(String message, Throwable cause) { super(message, cause); }
    public BedDAOUnAuthorizedException(Throwable cause) { super(cause); }
}
