package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Src_options
{
    @SerializedName("src_info")
    @Expose
    private String src_info;

    @SerializedName("is_mediawiki")
    @Expose
    private String is_mediawiki;

    @SerializedName("is_wikipedia")
    @Expose
    private String is_wikipedia;

    @SerializedName("skip_abstract_paren")
    @Expose
    private String skip_abstract_paren;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("skip_icon")
    @Expose
    private String skip_icon;

    @SerializedName("directory")
    @Expose
    private String directory;

    @SerializedName("is_fanon")
    @Expose
    private String is_fanon;

    @SerializedName("source_skip")
    @Expose
    private String source_skip;

    @SerializedName("min_abstract_length")
    @Expose
    private String min_abstract_length;

    @SerializedName("skip_image_name")
    @Expose
    private String skip_image_name;

    @SerializedName("skip_abstract")
    @Expose
    private String skip_abstract;

    @SerializedName("skip_qr")
    @Expose
    private String skip_qr;

    @SerializedName("skip_end")
    @Expose
    private String skip_end;

    public String getSrc_info ()
    {
        return src_info;
    }

    public void setSrc_info (String src_info)
    {
        this.src_info = src_info;
    }

    public String getIs_mediawiki ()
    {
        return is_mediawiki;
    }

    public void setIs_mediawiki (String is_mediawiki)
    {
        this.is_mediawiki = is_mediawiki;
    }

    public String getIs_wikipedia ()
    {
        return is_wikipedia;
    }

    public void setIs_wikipedia (String is_wikipedia)
    {
        this.is_wikipedia = is_wikipedia;
    }

    public String getSkip_abstract_paren ()
    {
        return skip_abstract_paren;
    }

    public void setSkip_abstract_paren (String skip_abstract_paren)
    {
        this.skip_abstract_paren = skip_abstract_paren;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getSkip_icon ()
    {
        return skip_icon;
    }

    public void setSkip_icon (String skip_icon)
    {
        this.skip_icon = skip_icon;
    }

    public String getDirectory ()
    {
        return directory;
    }

    public void setDirectory (String directory)
    {
        this.directory = directory;
    }

    public String getIs_fanon ()
    {
        return is_fanon;
    }

    public void setIs_fanon (String is_fanon)
    {
        this.is_fanon = is_fanon;
    }

    public String getSource_skip ()
    {
        return source_skip;
    }

    public void setSource_skip (String source_skip)
    {
        this.source_skip = source_skip;
    }

    public String getMin_abstract_length ()
    {
        return min_abstract_length;
    }

    public void setMin_abstract_length (String min_abstract_length)
    {
        this.min_abstract_length = min_abstract_length;
    }

    public String getSkip_image_name ()
    {
        return skip_image_name;
    }

    public void setSkip_image_name (String skip_image_name)
    {
        this.skip_image_name = skip_image_name;
    }

    public String getSkip_abstract ()
    {
        return skip_abstract;
    }

    public void setSkip_abstract (String skip_abstract)
    {
        this.skip_abstract = skip_abstract;
    }

    public String getSkip_qr ()
    {
        return skip_qr;
    }

    public void setSkip_qr (String skip_qr)
    {
        this.skip_qr = skip_qr;
    }

    public String getSkip_end ()
    {
        return skip_end;
    }

    public void setSkip_end (String skip_end)
    {
        this.skip_end = skip_end;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [src_info = "+src_info+", is_mediawiki = "+is_mediawiki+", is_wikipedia = "+is_wikipedia+", skip_abstract_paren = "+skip_abstract_paren+", language = "+language+", skip_icon = "+skip_icon+", directory = "+directory+", is_fanon = "+is_fanon+", source_skip = "+source_skip+", min_abstract_length = "+min_abstract_length+", skip_image_name = "+skip_image_name+", skip_abstract = "+skip_abstract+", skip_qr = "+skip_qr+", skip_end = "+skip_end+"]";
    }
}
