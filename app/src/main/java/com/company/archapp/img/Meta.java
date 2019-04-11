package com.company.archapp.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta
{

   // private null src_url;

    @SerializedName("example_query")
    @Expose
    private String example_query;

    @SerializedName("js_callback_name")
    @Expose
    private String js_callback_name;

    @SerializedName("repo")
    @Expose
    private String repo;

    @SerializedName("perl_module")
    @Expose
    private String perl_module;


   // private null dev_date;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("unsafe")
    @Expose
    private String unsafe;

    @SerializedName("src_name")
    @Expose
    private String src_name;


  //  private null blockgroup;

    @SerializedName("maintainer")
    @Expose
    private Maintainer maintainer;

    @SerializedName("src_domain")
    @Expose
    private String src_domain;

    @SerializedName("tab")
    @Expose
    private String tab;


 //   private null is_stackexchange;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("production_state")
    @Expose
    private String production_state;

    @SerializedName("src_id")
    @Expose
    private String src_id;

   // private null designer;

    @SerializedName("dev_milestone")
    @Expose
    private String dev_milestone;

   // private null attribution;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("topic")
    @Expose
    private String[] topic;

    @SerializedName("signal_from")
    @Expose
    private String signal_from;

  //  private null producer;

    @SerializedName("developer")
    @Expose
    private Developer[] developer;

  //  private null created_date;

  //  private null live_date;

    @SerializedName("src_options")
    @Expose
    private Src_options src_options;

    @SerializedName("status")
    @Expose
    private String status;

  //  public null getSrc_url ()
{
  //  return src_url;
}

  //  public void setSrc_url (null src_url)
    {
   //     this.src_url = src_url;
    }

    public String getExample_query ()
    {
        return example_query;
    }

    public void setExample_query (String example_query)
    {
        this.example_query = example_query;
    }

    public String getJs_callback_name ()
    {
        return js_callback_name;
    }

    public void setJs_callback_name (String js_callback_name)
    {
        this.js_callback_name = js_callback_name;
    }

    public String getRepo ()
    {
        return repo;
    }

    public void setRepo (String repo)
    {
        this.repo = repo;
    }

    public String getPerl_module ()
    {
        return perl_module;
    }

    public void setPerl_module (String perl_module)
    {
        this.perl_module = perl_module;
    }

   // public null getDev_date ()
{
   // return dev_date;
}

  //  public void setDev_date (null dev_date)
    {
    //    this.dev_date = dev_date;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getUnsafe ()
    {
        return unsafe;
    }

    public void setUnsafe (String unsafe)
    {
        this.unsafe = unsafe;
    }

    public String getSrc_name ()
    {
        return src_name;
    }

    public void setSrc_name (String src_name)
    {
        this.src_name = src_name;
    }

  //  public null getBlockgroup ()
{
  //  return blockgroup;
}

  //  public void setBlockgroup (null blockgroup)
    {
   //     this.blockgroup = blockgroup;
    }

    public Maintainer getMaintainer ()
    {
        return maintainer;
    }

    public void setMaintainer (Maintainer maintainer)
    {
        this.maintainer = maintainer;
    }

    public String getSrc_domain ()
    {
        return src_domain;
    }

    public void setSrc_domain (String src_domain)
    {
        this.src_domain = src_domain;
    }

    public String getTab ()
    {
        return tab;
    }

    public void setTab (String tab)
    {
        this.tab = tab;
    }

   // public null getIs_stackexchange ()
{
  //  return is_stackexchange;
}

   // public void setIs_stackexchange (null is_stackexchange)
    {
  //      this.is_stackexchange = is_stackexchange;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getProduction_state ()
    {
        return production_state;
    }

    public void setProduction_state (String production_state)
    {
        this.production_state = production_state;
    }

    public String getSrc_id ()
    {
        return src_id;
    }

    public void setSrc_id (String src_id)
    {
        this.src_id = src_id;
    }

   // public null getDesigner ()
{
   // return designer;
}

    //public void setDesigner (null designer)
    {
    //    this.designer = designer;
    }

    public String getDev_milestone ()
    {
        return dev_milestone;
    }

    public void setDev_milestone (String dev_milestone)
    {
        this.dev_milestone = dev_milestone;
    }

   // public null getAttribution ()
{
   // return attribution;
}

   // public void setAttribution (null attribution)
    {
   //     this.attribution = attribution;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String[] getTopic ()
    {
        return topic;
    }

    public void setTopic (String[] topic)
    {
        this.topic = topic;
    }

    public String getSignal_from ()
    {
        return signal_from;
    }

    public void setSignal_from (String signal_from)
    {
        this.signal_from = signal_from;
    }

   // public null getProducer ()
{
   // return producer;
}

   // public void setProducer (null producer)
    {
    //    this.producer = producer;
    }

    public Developer[] getDeveloper ()
    {
        return developer;
    }

    public void setDeveloper (Developer[] developer)
    {
        this.developer = developer;
    }

   // public null getCreated_date ()
{
   // return created_date;
}

   // public void setCreated_date (null created_date)
    {
   //     this.created_date = created_date;
    }

   // public null getLive_date ()
{
   // return live_date;
}

   // public void setLive_date (null live_date)
    {
    //    this.live_date = live_date;
    }

    public Src_options getSrc_options ()
    {
        return src_options;
    }

    public void setSrc_options (Src_options src_options)
    {
        this.src_options = src_options;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [src_url = "+", example_query = "+example_query+", js_callback_name = "+js_callback_name+", repo = "+repo+", perl_module = "+perl_module+", dev_date = "+", description = "+description+", unsafe = "+unsafe+", src_name = "+src_name+", blockgroup = "+", maintainer = "+maintainer+", src_domain = "+src_domain+", tab = "+tab+", is_stackexchange = "+", id = "+id+", production_state = "+production_state+", src_id = "+src_id+", designer = "+", dev_milestone = "+dev_milestone+", attribution = "+", name = "+name+", topic = "+topic+", signal_from = "+signal_from+", producer = "+", developer = "+developer+", created_date = "+", live_date = "+", src_options = "+src_options+", status = "+status+"]";
    }
}
