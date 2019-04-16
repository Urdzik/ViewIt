package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageDownloader {

    @SerializedName("results")
    @Expose
    private Results[] results;

    public Results[] getResults ()
    {
        return results;
    }
}
