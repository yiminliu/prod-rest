package com.bedrosians.bedlogic.resources;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import sun.misc.BASE64Decoder;

public class BasicAuthParser
{
    public static Map<String,String> parse(HttpHeaders headers)
    {
        Map<String,String> authInfo = null;
        
        List<String> authorizationList = headers.getRequestHeader("Authorization");
        if (authorizationList != null && !authorizationList.isEmpty())
        {
            String[] authorizationHeader = authorizationList.get(0).split("\\p{Space}+");
            if (authorizationHeader.length == 2 && SecurityContext.BASIC_AUTH.equalsIgnoreCase(authorizationHeader[0]))
            {
                BASE64Decoder decoder = new BASE64Decoder();
                try
                {
                    byte[]      decodedBytes = decoder.decodeBuffer(authorizationHeader[1]);
                    String      decodedString = new String(decodedBytes);
                    String[]    authInfoArray = decodedString.split(":");
                    
                    if (authInfoArray.length == 1)
                    {
                        authInfo = new HashMap<String,String>();
                        authInfo.put("user", authInfoArray[0]);
                        authInfo.put("password", "");
                    }
                    else if (authInfoArray.length == 2)
                    {
                        authInfo = new HashMap<String,String>();
                        authInfo.put("user", authInfoArray[0]);
                        authInfo.put("password", authInfoArray[1]);
                    }
                }
                catch (java.io.IOException e)
                {
                }
            }
        }
        
        return authInfo;
    }
}
