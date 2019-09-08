package com.example.communitynoticesystem;

public class Posts {

    public String uid, time, date, description,name;
    public Posts(){

    }

    public Posts(String uid, String time, String date, String description, String name){
        this.uid = uid;
        this.time = time;
        this.date = date;
        this.description = description;
        this.name = name;


    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getUid() {
        return uid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
