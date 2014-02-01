package com.bedrosians.bedlogic.exception;

import com.bedrosians.bedlogic.exception.BedDAOException;

public class BedDAOInternalException extends BedDAOException
{
    public BedDAOInternalException() { super(); }
    public BedDAOInternalException(String message) { super(message); }
    public BedDAOInternalException(String message, Throwable cause) { super(message, cause); }
    public BedDAOInternalException(Throwable cause) { super(cause); }
}
