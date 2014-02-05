package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class Prices
{
    private JSONObject  data;
    
    public Prices(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}