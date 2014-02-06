package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class SlabCosts
{
    private JSONObject  data;
    
    public SlabCosts(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}