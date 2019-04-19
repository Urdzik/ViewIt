package com.company.archapp.image.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class for retrofit
 */
public class ImageDownloader {

    @SerializedName("results")
    @Expose
    private Results[] results;

    public Results[] getResults() {
        return results;
    }
}
