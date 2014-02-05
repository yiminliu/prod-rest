package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class Locations
{
    private JSONObject  data;
    
    public Locations(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}