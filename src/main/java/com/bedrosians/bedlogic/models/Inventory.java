package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class Inventory
{
    private JSONObject  data;
    
    public Inventory(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}