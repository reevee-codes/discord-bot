package org.borb.commands.model.nytmostpopular;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {

    private Long id;

    private String url;

    private String section;

    private String subsection;

    private String byline;

    private String title;

    @JsonProperty("abstract")
    private String summary;

    private Media[] media;

    private String imageUrl;

    public Article(Long id, String url, String section, String subsection, String byline, String title, String summary, Media[] media, String imageUrl) {
        this.id = id;
        this.url = url;
        this.section = section;
        this.subsection = subsection;
        this.byline = byline;
        this.title = title;
        this.summary = summary;
        this.media = media;
        this.imageUrl = imageUrl;
    }

    public Article(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Media[] getMedia() {
        return media;
    }

    public void setMedia(Media[] media) {
        this.media = media;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
