package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Maintainer
{
    @SerializedName("github")
    @Expose
    private String github;

    public String getGithub ()
    {
        return github;
    }

    public void setGithub (String github)
    {
        this.github = github;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [github = "+github+"]";
    }
}
