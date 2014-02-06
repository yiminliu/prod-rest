package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class SlabInventory
{
    private JSONObject  data;
    
    public SlabInventory(JSONObject inData)
    {
        this.data = inData;
    }
    
    public String toJSONString()
    {
        return this.data.toString();
    }
}