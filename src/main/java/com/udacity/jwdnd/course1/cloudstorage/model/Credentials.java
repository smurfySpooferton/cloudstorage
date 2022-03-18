package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
    private Integer credentialsId;
    private String credentialsUrl;
    private String credentialsUsername;
    private String credentialsPassword;
    private Integer userId;

    public Credentials() {
    }

    public Credentials(Integer credentialsId, String credentialsUrl, String credentialsUsername, String credentialsPassword, Integer userId) {
        this.credentialsId = credentialsId;
        this.credentialsUrl = credentialsUrl;
        this.credentialsUsername = credentialsUsername;
        this.credentialsPassword = credentialsPassword;
        this.userId = userId;
    }

    public Integer getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(Integer credentialsId) {
        this.credentialsId = credentialsId;
    }

    public String getCredentialsUrl() {
        return credentialsUrl;
    }

    public void setCredentialsUrl(String credentialsUrl) {
        this.credentialsUrl = credentialsUrl;
    }

    public String getCredentialsUsername() {
        return credentialsUsername;
    }

    public void setCredentialsUsername(String credentialsUsername) {
        this.credentialsUsername = credentialsUsername;
    }

    public String getCredentialsPassword() {
        return credentialsPassword;
    }

    public void setCredentialsPassword(String credentialsPassword) {
        this.credentialsPassword = credentialsPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
