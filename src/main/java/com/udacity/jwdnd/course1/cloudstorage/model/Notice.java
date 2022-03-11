package com.udacity.jwdnd.course1.cloudstorage.model;

public class Notice {
    private String owner;
    private String id;
    private String title;
    private String description;

    public Notice() { }

    public Notice(String owner, String id, String title, String description) {
        this.owner = owner;
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
