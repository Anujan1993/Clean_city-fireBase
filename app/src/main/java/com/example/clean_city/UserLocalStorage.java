package com.example.clean_city;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStorage {

    public static final String SP_NAME="userdetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStorage(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }

    public void StoreUserData(User user){
        SharedPreferences.Editor SpEditor = userLocalDatabase.edit();
        SpEditor.putString("name",user.username);
        SpEditor.putString("Phone",user.Phone);
        SpEditor.putString("Address",user.Address);
        SpEditor.putString("Password",user.Password);
        SpEditor.commit();
    }

    public User getLockedInUser(){
        String name = userLocalDatabase.getString("name","");
        String Phone = userLocalDatabase.getString("Phone","");
        String Address = userLocalDatabase.getString("Address","");
        String Password = userLocalDatabase.getString("Password","");

        User StoredUser = new User(name,Address,Phone,Password);
        return StoredUser;
    }
    public void setUserLockedIn(boolean lockedIn){
        SharedPreferences.Editor SpEditor = userLocalDatabase.edit();
        SpEditor.putBoolean("LockedIn",lockedIn);
        SpEditor.commit();
    }

    public boolean getUserLockedIn(){
        if (userLocalDatabase.getBoolean("LockedIn",false)== true){
            return true;
        }
        else {
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor SpEditor = userLocalDatabase.edit();
        SpEditor.clear();
        SpEditor.commit();

    }
}
