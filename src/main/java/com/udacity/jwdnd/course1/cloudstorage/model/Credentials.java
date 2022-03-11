package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
    private String credentialsOwner;
    private String credentialsId;
    private String url;
    private String username;
    private String password;

    public String getCredentialsOwner() {
        return credentialsOwner;
    }

    public void setCredentialsOwner(String credentialsOwner) {
        this.credentialsOwner = credentialsOwner;
    }

    public String getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(String credentialsId) {
        this.credentialsId = credentialsId;
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
