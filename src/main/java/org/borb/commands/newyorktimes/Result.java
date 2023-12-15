package org.borb.commands.newyorktimes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class Result{
    public String section;
    public String subsection;
    public String title;
    @JsonProperty("abstract")
    public String myabstract;
    public String url;
    public String uri;
    public String byline;
    public String item_type;
    public Date updated_date;
    public Date created_date;
    public Date published_date;
    public String material_type_facet;
    public String kicker;
    public ArrayList<String> des_facet;
    public ArrayList<String> org_facet;
    public ArrayList<String> per_facet;
    public ArrayList<String> geo_facet;
    public ArrayList<Multimedium> multimedia;
    public String short_url;
}