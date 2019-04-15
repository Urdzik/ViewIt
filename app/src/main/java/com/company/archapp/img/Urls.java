package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Urls {
    @SerializedName("small")
    @Expose
    private String small;

    @SerializedName("thumb")
    @Expose
    private String thumb;

    @SerializedName("raw")
    @Expose
    private String raw;

    @SerializedName("regular")
    @Expose
    private String regular;

    @SerializedName("full")
    @Expose
    private String full;

    public String getSmall ()
    {
        return small;
    }

    public void setSmall (String small)
    {
        this.small = small;
    }

    public String getThumb ()
    {
        return thumb;
    }

    public void setThumb (String thumb)
    {
        this.thumb = thumb;
    }

    public String getRaw ()
    {
        return raw;
    }

    public void setRaw (String raw)
    {
        this.raw = raw;
    }

    public String getRegular ()
    {
        return regular;
    }

    public void setRegular (String regular)
    {
        this.regular = regular;
    }

    public String getFull ()
    {
        return full;
    }

    public void setFull (String full)
    {
        this.full = full;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [small = "+small+", thumb = "+thumb+", raw = "+raw+", regular = "+regular+", full = "+full+"]";
    }
}
