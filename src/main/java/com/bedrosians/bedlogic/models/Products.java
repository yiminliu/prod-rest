package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class Products
{
    private JSONObject  data;
    
    public Products(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}