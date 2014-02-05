package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class PromoSeries
{
    private JSONObject  data;
    
    public PromoSeries(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}