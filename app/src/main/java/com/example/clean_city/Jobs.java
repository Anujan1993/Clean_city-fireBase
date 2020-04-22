package com.example.clean_city;

import java.util.ArrayList;

public class Jobs {
    private ArrayList<String> wastes;
    private String userID;
    private String date;

    public Jobs(){

    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getWastes() { return String.valueOf(wastes); }

    public void setWastes(ArrayList<String> wastes) { this.wastes = wastes; }

    public String getUserID() { return userID; }

    public void setUserID(String userID) { this.userID = userID; }
}
