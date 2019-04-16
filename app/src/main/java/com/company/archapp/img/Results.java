package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("urls")
    @Expose
    private Urls urls;

    public Urls getUrls ()
    {
        return urls;
    }

}
