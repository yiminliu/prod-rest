package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class Costs
{
    private JSONObject  data;
    
    public Costs(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}