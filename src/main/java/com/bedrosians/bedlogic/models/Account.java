package com.bedrosians.bedlogic.models;

import java.lang.String;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account
{
    private String custcd;
    private String coname;
    private String coaddr1;
    private String coaddr2;
    private String cocity;
    private String costatecd;
    private String cozip;
    private String cocountrycd;
    
    public Account()
    {
        this.custcd = "";
        this.coname = "";
        this.coaddr1 = "";
        this.coaddr2 = "";
        this.cocity = "";
        this.costatecd = "";
        this.cozip = "";
        this.cocountrycd = "";
    }
    
    public Account(String custcd, String coname, String coaddr1, String coaddr2, String cocity, String costatecd, String cozip, String cocountrycd)
    {
        this.custcd = custcd;
        this.coname = coname;
        this.coaddr1 = coaddr1;
        this.coaddr2 = coaddr2;
        this.cocity = cocity;
        this.costatecd = costatecd;
        this.cozip = cozip;
        this.cocountrycd = cocountrycd;
    }
    
    public void setCustcd(String custcd)
    {
        this.custcd = custcd;
    }
    
    public String getCustcd()
    {
        return this.custcd;
    }

    public void setConame(String coname)
    {
        this.coname = coname;
    }
    
    public String getConame()
    {
        return this.coname;
    }

    public void setCoaddr1(String coaddr1)
    {
        this.coaddr1 = coaddr1;
    }
    
    public String getCoaddr1()
    {
        return this.coaddr1;
    }
    
    public void setCoaddr2(String coaddr2)
    {
        this.coaddr2 = coaddr2;
    }
    
    public String getCoaddr2()
    {
        return this.coaddr2;
    }
    
    public void setCocity(String cocity)
    {
        this.cocity = cocity;
    }
    
    public String getCocity()
    {
        return this.cocity;
    }
    
    public void setCostatecd(String costatecd)
    {
        this.costatecd = costatecd;
    }
    
    public String getCostatecd()
    {
        return this.costatecd;
    }
    
    public void setCozip(String cozip)
    {
        this.cozip = cozip;
    }
    
    public String getCozip()
    {
        return this.cozip;
    }
    
    public void setCocountrycd(String cocountrycd)
    {
        this.cocountrycd = cocountrycd;
    }
    
    public String getCocountrycd()
    {
        return this.cocountrycd;
    }
}