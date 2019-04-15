package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {
    @SerializedName("title")
    @Expose
    private String title;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+"]";
    }
}
