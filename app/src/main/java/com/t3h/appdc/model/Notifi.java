package com.t3h.appdc.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Notifi implements Serializable {
    @SerializedName("id")
    private int id ;
    @SerializedName("cate_noti")
    private String name;
    @SerializedName("mess")
    private String message;
    @SerializedName("dateup")
    private String date;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("baiviet_id")
    private String baiviet_id;

    public String getFullname() {
        return fullname;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBaiviet_id() {
        return baiviet_id;
    }

    public void setBaiviet_id(String baiviet_id) {
        this.baiviet_id = baiviet_id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getId() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
