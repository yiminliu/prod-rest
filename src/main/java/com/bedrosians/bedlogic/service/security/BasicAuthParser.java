package com.bedrosians.bedlogic.service.security;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.codec.binary.Base64;

import com.bedrosians.bedlogic.exception.UnauthorizedException;

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
            	Base64 decoder = new Base64();
                byte[]      decodedBytes = decoder.decode(authorizationHeader[1].getBytes());
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
            else
               throw new UnauthorizedException("User information format is incorrect.");
        }
        
        return authInfo;
    }
}
