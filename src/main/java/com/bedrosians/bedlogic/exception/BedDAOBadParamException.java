package com.bedrosians.bedlogic.exception;

import com.bedrosians.bedlogic.exception.BedDAOException;

public class BedDAOBadParamException extends BedDAOException
{
    public BedDAOBadParamException() { super(); }
    public BedDAOBadParamException(String message) { super(message); }
    public BedDAOBadParamException(String message, Throwable cause) { super(message, cause); }
    public BedDAOBadParamException(Throwable cause) { super(cause); }
}
