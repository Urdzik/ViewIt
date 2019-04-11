package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Icon
{
    @SerializedName("Height")
    @Expose

    private String Height;

    @SerializedName("Width")
    @Expose
    private String Width;

    @SerializedName("URL")
    @Expose
    private String URL;

    public String getHeight ()
    {
        return Height;
    }

    public void setHeight (String Height)
    {
        this.Height = Height;
    }

    public String getWidth ()
    {
        return Width;
    }

    public void setWidth (String Width)
    {
        this.Width = Width;
    }

    public String getURL ()
    {
        return URL;
    }

    public void setURL (String URL)
    {
        this.URL = URL;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Height = "+Height+", Width = "+Width+", URL = "+URL+"]";
    }
}
