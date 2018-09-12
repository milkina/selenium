package com.examclouds.model;

/**
 * Created by Tatyana on 29.01.2017.
 */
public class Article {
    private String keywords;
    private String description;
    private String title;
    private String text;
    private String imgUrl;

    public Article(String keywords, String description, String title, String text, String imgUrl) {
        this.keywords = keywords;
        this.description = description;
        this.title = title;
        this.text = text;
        this.imgUrl = imgUrl;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
