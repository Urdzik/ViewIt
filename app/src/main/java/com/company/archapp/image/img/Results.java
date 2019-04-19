package com.company.archapp.image.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class for retrofit
 */
public class Results {

    @SerializedName("urls")
    @Expose
    private Urls urls;

    public Urls getUrls() {
        return urls;
    }

}
