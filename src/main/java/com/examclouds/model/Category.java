package com.examclouds.model;

/**
 * Created by Tatyana on 18.06.2016.
 */
public class Category {
    private String name;
    private String pathName;
    private String image;
    private String description;
    private String text;
    private String keywords;
    private Test test;

    public Category() {
    }

    public Category(String name, String pathName, Test test, String description) {
        this.name = name;
        this.pathName = pathName;
        this.test = test;
        this.description = description;
    }

    public Category(String name, String pathName, String description) {
        this.name = name;
        this.pathName = pathName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getTitle() {
        return name + " - " + test.getName();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
