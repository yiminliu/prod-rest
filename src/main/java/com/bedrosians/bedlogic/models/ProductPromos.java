package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class ProductPromos
{
    private JSONObject  data;
    
    public ProductPromos(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}