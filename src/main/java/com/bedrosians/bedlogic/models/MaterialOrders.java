package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class MaterialOrders
{
    private JSONObject  data;
    
    public MaterialOrders(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}