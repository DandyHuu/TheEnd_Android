package com.t3h.appdc.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comment  implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("fullname")
    private String name;
    @SerializedName("comment")
    private String comment;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("date")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
