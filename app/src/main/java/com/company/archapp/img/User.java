package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("total_photos")
    @Expose
    private String total_photos;

    @SerializedName("accepted_tos")
    @Expose
    private String accepted_tos;

    @SerializedName("last_name")
    @Expose
    private String last_name;

    @SerializedName("bio")
    @Expose
    private String bio;

    @SerializedName("total_likes")
    @Expose
    private String total_likes;

    @SerializedName("portfolio_url")
    @Expose
    private String portfolio_url;

    @SerializedName("profile_image")
    @Expose
    private Profile_image profile_image;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("links")
    @Expose
    private Links links;

    @SerializedName("total_collections")
    @Expose
    private String total_collections;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("first_name")
    @Expose
    private String first_name;

    @SerializedName("instagram_username")
    @Expose
    private String instagram_username;

    @SerializedName("username")
    @Expose
    private String username;

    public String getTotal_photos ()
    {
        return total_photos;
    }

    public void setTotal_photos (String total_photos)
    {
        this.total_photos = total_photos;
    }

    public String getAccepted_tos ()
    {
        return accepted_tos;
    }

    public void setAccepted_tos (String accepted_tos)
    {
        this.accepted_tos = accepted_tos;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getBio ()
    {
        return bio;
    }

    public void setBio (String bio)
    {
        this.bio = bio;
    }

    public String getTotal_likes ()
    {
        return total_likes;
    }

    public void setTotal_likes (String total_likes)
    {
        this.total_likes = total_likes;
    }

    public String getPortfolio_url ()
    {
        return portfolio_url;
    }

    public void setPortfolio_url (String portfolio_url)
    {
        this.portfolio_url = portfolio_url;
    }

    public Profile_image getProfile_image ()
    {
        return profile_image;
    }

    public void setProfile_image (Profile_image profile_image)
    {
        this.profile_image = profile_image;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public Links getLinks ()
    {
        return links;
    }

    public void setLinks (Links links)
    {
        this.links = links;
    }

    public String getTotal_collections ()
    {
        return total_collections;
    }

    public void setTotal_collections (String total_collections)
    {
        this.total_collections = total_collections;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getInstagram_username ()
    {
        return instagram_username;
    }

    public void setInstagram_username (String instagram_username)
    {
        this.instagram_username = instagram_username;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total_photos = "+total_photos+", accepted_tos = "+accepted_tos+", twitter_username = "+", last_name = "+last_name+", bio = "+bio+", total_likes = "+total_likes+", portfolio_url = "+portfolio_url+", profile_image = "+profile_image+", updated_at = "+updated_at+", name = "+name+", location = "+location+", links = "+links+", total_collections = "+total_collections+", id = "+id+", first_name = "+first_name+", instagram_username = "+instagram_username+", username = "+username+"]";
    }
}
