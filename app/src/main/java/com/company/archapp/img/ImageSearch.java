package com.company.archapp.img;

//Submit another JSON or XML
  //      ImageSearch.java Meta.java Developer.java Src_options.java Maintainer.java Icon.java Topics.java RelatedTopics.java Icon.java

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageSearch
{
    @SerializedName("Entity")
    @Expose
    private String Entity;

    @SerializedName("DefinitionSource")
    @Expose
    private String DefinitionSource;

    @SerializedName("RelatedTopics")
    @Expose
    private com.company.archapp.img.RelatedTopics[] RelatedTopics;

    @SerializedName("Heading")
    @Expose
    private String Heading;

    @SerializedName("Abstract")
    @Expose
    private String Abstract;

    @SerializedName("AbstractURL")
    @Expose
    private String AbstractURL;

    @SerializedName("DefinitionURL")
    @Expose
    private String DefinitionURL;

    @SerializedName("Definition")
    @Expose
    private String Definition;

    @SerializedName("Redirect")
    @Expose
    private String Redirect;

    @SerializedName("AbstractText")
    @Expose
    private String AbstractText;

    @SerializedName("Image")
    @Expose
    private String Image;

    @SerializedName("Infobox")
    @Expose
    private String Infobox;

    @SerializedName("Answer")
    @Expose
    private String Answer;

    @SerializedName("Type")
    @Expose
    private String Type;

    @SerializedName("ImageIsLogo")
    @Expose
    private String ImageIsLogo;

    @SerializedName("ImageHeight")
    @Expose
    private String ImageHeight;

    @SerializedName("Results")
    @Expose
    private String[] Results;

    @SerializedName("ImageWidth")
    @Expose
    private String ImageWidth;

    @SerializedName("meta")
    @Expose
    private Meta meta;

    @SerializedName("AbstractSource")
    @Expose
    private String AbstractSource;

    @SerializedName("AnswerType")
    @Expose
    private String AnswerType;

    public String getEntity ()
    {
        return Entity;
    }

    public void setEntity (String Entity)
    {
        this.Entity = Entity;
    }

    public String getDefinitionSource ()
    {
        return DefinitionSource;
    }

    public void setDefinitionSource (String DefinitionSource)
    {
        this.DefinitionSource = DefinitionSource;
    }

    public com.company.archapp.img.RelatedTopics[] getRelatedTopics ()
    {
        return RelatedTopics;
    }

    public void setRelatedTopics (RelatedTopics[] RelatedTopics)
    {
        this.RelatedTopics = RelatedTopics;
    }

    public String getHeading ()
    {
        return Heading;
    }

    public void setHeading (String Heading)
    {
        this.Heading = Heading;
    }

    public String getAbstract ()
    {
        return Abstract;
    }

    public void setAbstract (String Abstract)
    {
        this.Abstract = Abstract;
    }

    public String getAbstractURL ()
    {
        return AbstractURL;
    }

    public void setAbstractURL (String AbstractURL)
    {
        this.AbstractURL = AbstractURL;
    }

    public String getDefinitionURL ()
    {
        return DefinitionURL;
    }

    public void setDefinitionURL (String DefinitionURL)
    {
        this.DefinitionURL = DefinitionURL;
    }
/*
    public ImageSearch(String entity, String definitionSource, com.example.imagedownload.img.RelatedTopics[] relatedTopics, String heading, String anAbstract, String abstractURL, String definitionURL, String definition, String redirect, String abstractText, String image, String infobox, String answer, String type, String imageIsLogo, String imageHeight, String[] results, String imageWidth, Meta meta, String abstractSource, String answerType) {
        Entity = entity;
        DefinitionSource = definitionSource;
        RelatedTopics = relatedTopics;
        Heading = heading;
        Abstract = anAbstract;
        AbstractURL = abstractURL;
        DefinitionURL = definitionURL;
        Definition = definition;
        Redirect = redirect;
        AbstractText = abstractText;
        Image = image;
        Infobox = infobox;
        Answer = answer;
        Type = type;
        ImageIsLogo = imageIsLogo;
        ImageHeight = imageHeight;
        Results = results;
        ImageWidth = imageWidth;
        this.meta = meta;
        AbstractSource = abstractSource;
        AnswerType = answerType;
    }
*/
    public String getDefinition ()
    {
        return Definition;
    }

    public void setDefinition (String Definition)
    {
        this.Definition = Definition;
    }

    public String getRedirect ()
    {
        return Redirect;
    }

    public void setRedirect (String Redirect)
    {
        this.Redirect = Redirect;
    }

    public String getAbstractText ()
    {
        return AbstractText;
    }

    public void setAbstractText (String AbstractText)
    {
        this.AbstractText = AbstractText;
    }

    public String getImage ()
    {
        return Image;
    }

    public void setImage (String Image)
    {
        this.Image = Image;
    }

    public String getInfobox ()
    {
        return Infobox;
    }

    public void setInfobox (String Infobox)
    {
        this.Infobox = Infobox;
    }

    public String getAnswer ()
    {
        return Answer;
    }

    public void setAnswer (String Answer)
    {
        this.Answer = Answer;
    }

    public String getType ()
    {
        return Type;
    }

    public void setType (String Type)
    {
        this.Type = Type;
    }

    public String getImageIsLogo ()
    {
        return ImageIsLogo;
    }

    public void setImageIsLogo (String ImageIsLogo)
    {
        this.ImageIsLogo = ImageIsLogo;
    }

    public String getImageHeight ()
    {
        return ImageHeight;
    }

    public void setImageHeight (String ImageHeight)
    {
        this.ImageHeight = ImageHeight;
    }

    public String[] getResults ()
    {
        return Results;
    }

    public void setResults (String[] Results)
    {
        this.Results = Results;
    }

    public String getImageWidth ()
    {
        return ImageWidth;
    }

    public void setImageWidth (String ImageWidth)
    {
        this.ImageWidth = ImageWidth;
    }

    public Meta getMeta ()
    {
        return meta;
    }

    public void setMeta (Meta meta)
    {
        this.meta = meta;
    }

    public String getAbstractSource ()
    {
        return AbstractSource;
    }

    public void setAbstractSource (String AbstractSource)
    {
        this.AbstractSource = AbstractSource;
    }

    public String getAnswerType ()
    {
        return AnswerType;
    }

    public void setAnswerType (String AnswerType)
    {
        this.AnswerType = AnswerType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Entity = "+Entity+", DefinitionSource = "+DefinitionSource+", RelatedTopics = "+RelatedTopics+", Heading = "+Heading+", Abstract = "+Abstract+", AbstractURL = "+AbstractURL+", DefinitionURL = "+DefinitionURL+", Definition = "+Definition+", Redirect = "+Redirect+", AbstractText = "+AbstractText+", Image = "+Image+", Infobox = "+Infobox+", Answer = "+Answer+", Type = "+Type+", ImageIsLogo = "+ImageIsLogo+", ImageHeight = "+ImageHeight+", Results = "+Results+", ImageWidth = "+ImageWidth+", meta = "+meta+", AbstractSource = "+AbstractSource+", AnswerType = "+AnswerType+"]";
    }
}


