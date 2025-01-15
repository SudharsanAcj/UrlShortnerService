package com.utills.urlShortner.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "url_master")
public class UrlMaster {

    @Id
    private long id;

    private String longUrl;
    private String shortUrl;
    private String customAliasName;
    private int expiryEpochtime;
    private int modifiedEpochtime;
    private int createdEpochtime;
    private String modifiedBy;
    private String createdBy;
    private boolean status;
    private boolean isDeleted;

    // Constructors, getters, and setters

    public UrlMaster() {
    }

    public UrlMaster(String longUrl, String shortUrl, String customAliasName, int expiryEpochtime, int modifiedEpochtime, int createdEpochtime, String modifiedBy, String createdBy, boolean status, boolean isDeleted) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.customAliasName = customAliasName;
        this.expiryEpochtime = expiryEpochtime;
        this.modifiedEpochtime = modifiedEpochtime;
        this.createdEpochtime = createdEpochtime;
        this.modifiedBy = modifiedBy;
        this.createdBy = createdBy;
        this.status = status;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getCustomAliasName() {
        return customAliasName;
    }

    public void setCustomAliasName(String customAliasName) {
        this.customAliasName = customAliasName;
    }

    public int getExpiryEpochtime() {
        return expiryEpochtime;
    }

    public void setExpiryEpochtime(int expiryEpochtime) {
        this.expiryEpochtime = expiryEpochtime;
    }

    public int getModifiedEpochtime() {
        return modifiedEpochtime;
    }

    public void setModifiedEpochtime(int modifiedEpochtime) {
        this.modifiedEpochtime = modifiedEpochtime;
    }

    public int getCreatedEpochtime() {
        return createdEpochtime;
    }

    public void setCreatedEpochtime(int createdEpochtime) {
        this.createdEpochtime = createdEpochtime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
