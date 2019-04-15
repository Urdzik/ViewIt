package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageDownloader {
    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("total_pages")
    @Expose
    private String total_pages;

    @SerializedName("results")
    @Expose
    private Results[] results;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (String total_pages)
    {
        this.total_pages = total_pages;
    }

    public Results[] getResults ()
    {
        return results;
    }

    public void setResults (Results[] results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", total_pages = "+total_pages+", results = "+results+"]";
    }
}
