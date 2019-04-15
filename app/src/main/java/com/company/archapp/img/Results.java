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

    @SerializedName("tags")
    @Expose
    private Tags[] tags;

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

    @SerializedName("links")
    @Expose
    private Links links;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("categories")
    @Expose
    private String[] categories;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("height")
    @Expose
    private String height;

    @SerializedName("likes")
    @Expose
    private String likes;

    public String[] getCurrent_user_collections ()
    {
        return current_user_collections;
    }

    public void setCurrent_user_collections (String[] current_user_collections)
    {
        this.current_user_collections = current_user_collections;
    }

    public String getColor ()
    {
        return color;
    }

    public void setColor (String color)
    {
        this.color = color;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getSponsored ()
    {
        return sponsored;
    }

    public void setSponsored (String sponsored)
    {
        this.sponsored = sponsored;
    }

    public String getLiked_by_user ()
    {
        return liked_by_user;
    }

    public void setLiked_by_user (String liked_by_user)
    {
        this.liked_by_user = liked_by_user;
    }

    public Tags[] getTags ()
    {
        return tags;
    }

    public void setTags (Tags[] tags)
    {
        this.tags = tags;
    }

    public Urls getUrls ()
    {
        return urls;
    }

    public void setUrls (Urls urls)
    {
        this.urls = urls;
    }

    public String getAlt_description ()
    {
        return alt_description;
    }

    public void setAlt_description (String alt_description)
    {
        this.alt_description = alt_description;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public Links getLinks ()
    {
        return links;
    }

    public void setLinks (Links links)
    {
        this.links = links;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String[] getCategories ()
    {
        return categories;
    }

    public void setCategories (String[] categories)
    {
        this.categories = categories;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getLikes ()
    {
        return likes;
    }

    public void setLikes (String likes)
    {
        this.likes = likes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [current_user_collections = "+current_user_collections+", color = "+color+", created_at = "+created_at+", description = "+description+", sponsored = "+sponsored+", liked_by_user = "+liked_by_user+", tags = "+tags+", urls = "+urls+", alt_description = "+alt_description+", updated_at = "+updated_at+", width = "+width+", links = "+links+", id = "+id+", categories = "+categories+", user = "+user+", height = "+height+", likes = "+likes+"]";
    }
}
