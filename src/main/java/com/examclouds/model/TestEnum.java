package com.examclouds.model;

/**
 * Created by Tatyana on 29.05.2016.
 */
public enum TestEnum {
    OCJP("ocjp", "OCJP 6"), JPA("jpa", "OCEJPAD 6"), WS("web-services", "Web Services");
    private String name;
    private String title;

    TestEnum(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getLocatorName() {
        return String.format("a[id='%s']", name);
    }

    public String getTitleName() {
        return String.format("Free %s Tutorial on ExamClouds", title);
    }
}
