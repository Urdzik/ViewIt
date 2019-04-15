package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile_image {
    @SerializedName("small")
    @Expose
    private String small;

    @SerializedName("large")
    @Expose
    private String large;

    @SerializedName("medium")
    @Expose
    private String medium;

    public String getSmall ()
    {
        return small;
    }

    public void setSmall (String small)
    {
        this.small = small;
    }

    public String getLarge ()
    {
        return large;
    }

    public void setLarge (String large)
    {
        this.large = large;
    }

    public String getMedium ()
    {
        return medium;
    }

    public void setMedium (String medium)
    {
        this.medium = medium;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [small = "+small+", large = "+large+", medium = "+medium+"]";
    }
}
