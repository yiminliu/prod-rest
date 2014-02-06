package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class Accounts
{
    private JSONObject  data;
    
    public Accounts(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}
