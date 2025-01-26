package org.borb.commands.newsapi.model;

import java.util.List;

public class NewsApiResponse {
    private String status;
    private List<org.borb.commands.newsapi.model.NewsArticle> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List <NewsArticle> articles) {
        this.articles = articles;
    }
}