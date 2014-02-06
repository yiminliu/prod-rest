package com.bedrosians.bedlogic.models;

import net.minidev.json.JSONObject;

public class ProductSeries
{
    private JSONObject  productSeriesData;
    
    public ProductSeries(JSONObject inProductSeriesData)
    {
        this.productSeriesData = inProductSeriesData;
    }
    
    public String toJSONString()
    {
        return this.productSeriesData.toString();
    }
}