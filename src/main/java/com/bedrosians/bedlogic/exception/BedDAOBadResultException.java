package com.bedrosians.bedlogic.exception;

import com.bedrosians.bedlogic.exception.BedDAOException;

public class BedDAOBadResultException extends BedDAOException
{
    public BedDAOBadResultException() { super(); }
    public BedDAOBadResultException(String message) { super(message); }
    public BedDAOBadResultException(String message, Throwable cause) { super(message, cause); }
    public BedDAOBadResultException(Throwable cause) { super(cause); }
}
