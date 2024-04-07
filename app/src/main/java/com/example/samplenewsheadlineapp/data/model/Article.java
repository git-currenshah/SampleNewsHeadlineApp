package com.example.samplenewsheadlineapp.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Article implements Comparable<Article>{

    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("urlToImage")
    private String urlToImage;

    public LocalDate publishDate;

    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public LocalDate getPublishDate() {
        if(publishDate == null) {
            publishDate =  LocalDate.parse(getPublishedAt(), inputFormatter);
        }
        return publishDate;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @Override
    public int compareTo(Article o) {
        if (getPublishDate() == null || o.getPublishDate() == null)
            return 0;
        return o.getPublishDate().compareTo(getPublishDate());
    }
}
