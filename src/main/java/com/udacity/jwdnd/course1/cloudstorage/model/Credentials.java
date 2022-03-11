package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
    private String owner;
    private String id;
    private String url;
    private String username;
    private String password;

    public Credentials() { }

    public Credentials(String owner, String id, String url, String username, String password) {
        this.owner = owner;
        this.id = id;
        this.url = url;
        this.username = username;
        this.password = password;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
