package com.bedrosians.bedlogic.resources.v1;

import java.util.Map;
import javax.ws.rs.core.HttpHeaders;

public class UserCodeParser
{
    private Map<String,String> authInfo;
    
    public UserCodeParser(HttpHeaders headers)
    {
        this.authInfo = BasicAuthParser.parse(headers);
    }
    
    public boolean isValidFormat()
    {
        String  userType = this.getUserType();
        String  userCode = this.getUserCode();
        boolean validFormat;

        if (userType == null || userCode == null)
        {
            validFormat = false;
        }
        else if ("guest".equals(userType) && userCode.isEmpty())
        {
            validFormat = true;
        }
        else if ("keymark".equals(userType) && !userCode.isEmpty())
        {
            validFormat = true;
        }
        else
        {
            validFormat = false;
        }

        return validFormat;
    }
    
    public String getUserType()
    {
        return (this.authInfo != null) ? this.authInfo.get("user") : null;
    }
    
    public String getUserCode()
    {
        return (this.authInfo != null) ? this.authInfo.get("password") : null;
    }
}
