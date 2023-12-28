package org.borb.commands.newyorktimes.model.nytmostpopular;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thumbnail {

    @JsonProperty("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
