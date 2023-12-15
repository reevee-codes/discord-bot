package org.borb.commands.model.nytmostpopular;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Media {

    @JsonProperty("type")
    private String type;

    private String subtype;

    @JsonProperty("caption")
    private String caption;

    private String url;

    @JsonProperty("media-metadata")
    private Thumbnail[] media_metadata;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Thumbnail[] getMedia_metadata() {
        return media_metadata;
    }

    public void setMedia_metadata(Thumbnail[] media_metadata) {
        this.media_metadata = media_metadata;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
