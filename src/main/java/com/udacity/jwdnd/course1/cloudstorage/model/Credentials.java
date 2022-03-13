package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
    private Integer id;
    private String url;
    private String username;
    private String password;
    private Integer userId;

    public Credentials() { }

    public Credentials(Integer id, String url, String username, String password, Integer userId) {
        this.id = id;
        this.url = url;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
