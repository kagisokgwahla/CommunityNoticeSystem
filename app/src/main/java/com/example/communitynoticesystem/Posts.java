package com.example.communitynoticesystem;

public class Posts {

    public String uid, time, date, description,name,location;
    public Posts(){

    }

    public Posts(String uid, String time, String date, String description, String name,String location){
        this.uid = uid;
        this.time = time;
        this.date = date;
        this.description = description;
        this.name = name;
        this.location = location;


    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
