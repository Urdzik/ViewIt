package com.company.archapp.img;

import com.company.archapp.img.Icon;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topics
{
    @SerializedName("Text")
    @Expose
    private String Text;

    @SerializedName("Icon")
    @Expose
    private com.company.archapp.img.Icon Icon;

    @SerializedName("FirstURL")
    @Expose
    private String FirstURL;

    @SerializedName("Result")
    @Expose
    private String Result;

    public String getText ()
    {
        return Text;
    }

    public void setText (String Text)
    {
        this.Text = Text;
    }

    public Icon getIcon ()
    {
        return Icon;
    }

    public void setIcon (Icon Icon)
    {
        this.Icon = Icon;
    }

    public String getFirstURL ()
    {
        return FirstURL;
    }

    public void setFirstURL (String FirstURL)
    {
        this.FirstURL = FirstURL;
    }

    public String getResult ()
    {
        return Result;
    }

    public void setResult (String Result)
    {
        this.Result = Result;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Text = "+Text+", Icon = "+Icon+", FirstURL = "+FirstURL+", Result = "+Result+"]";
    }
}
