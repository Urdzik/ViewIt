package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("current_user_collections")
    @Expose
    private String[] current_user_collections;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("sponsored")
    @Expose
    private String sponsored;

    @SerializedName("liked_by_user")
    @Expose
    private String liked_by_user;

    @SerializedName("urls")
    @Expose
    private Urls urls;

    @SerializedName("alt_description")
    @Expose
    private String alt_description;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("width")
    @Expose
    private String width;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("categories")
    @Expose
    private String[] categories;

    @SerializedName("height")
    @Expose
    private String height;

    @SerializedName("likes")
    @Expose
    private String likes;


    public Urls getUrls ()
    {
        return urls;
    }

}
