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
}
