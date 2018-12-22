package com.examclouds.model;

/**
 * Created by Tatyana on 09.12.2016.
 */
public class Test {
    private String pathName;
    private String name;
    private String tags;
    private String itomText;
    private Article article;
    private String languageCode;

    public Test(String pathName, String name) {
        this.pathName = pathName;
        this.name = name;
    }

    public Test(String pathName, String name, String tags) {
        this.pathName = pathName;
        this.name = name;
        this.tags = tags;
    }

    public Test(String pathName, String name, String tags, String itomText, Article article,String language) {
        this.pathName = pathName;
        this.name = name;
        this.tags = tags;
        this.article = article;
        this.itomText = itomText;
        this.languageCode = language;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getItomText() {
        return itomText;
    }

    public void setItomText(String itomText) {
        this.itomText = itomText;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
