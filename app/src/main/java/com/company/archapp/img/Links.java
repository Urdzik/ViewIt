package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {
    @SerializedName("download")
    @Expose
    private String download;

    @SerializedName("download_location")
    @Expose
    private String download_location;

    @SerializedName("self")
    @Expose
    private String self;

    @SerializedName("html")
    @Expose
    private String html;

    public String getDownload ()
    {
        return download;
    }

    public void setDownload (String download)
    {
        this.download = download;
    }

    public String getDownload_location ()
    {
        return download_location;
    }

    public void setDownload_location (String download_location)
    {
        this.download_location = download_location;
    }

    public String getSelf ()
    {
        return self;
    }

    public void setSelf (String self)
    {
        this.self = self;
    }

    public String getHtml ()
    {
        return html;
    }

    public void setHtml (String html)
    {
        this.html = html;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [download = "+download+", download_location = "+download_location+", self = "+self+", html = "+html+"]";
    }
}
