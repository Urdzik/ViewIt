package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Urls {

    @SerializedName("regular")
    @Expose
    private String regular;

    public String getRegular ()
    {
        return regular;
    }
}
