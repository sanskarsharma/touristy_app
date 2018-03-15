package com.technovate18.sanskar.touristy.models;

/**
 * Created by hadoop on 15/3/18.
 */

public class FeedPostModel {

    String timestamp;
    String title;
    String description;
    String author;
    String noticenumber;

    public FeedPostModel() {

    }

    public FeedPostModel(String timestamp, String title, String description, String author, String noticenumber) {
        this.timestamp = timestamp;
        this.title = title;
        this.description = description;
        this.author = author;
        this.noticenumber = noticenumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNoticenumber() {
        return noticenumber;
    }

    public void setNoticenumber(String noticenumber) {
        this.noticenumber = noticenumber;
    }


}
