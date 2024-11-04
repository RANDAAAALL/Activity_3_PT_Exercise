package com.example.activity_3_ipt;

public class FetchCurrentUser {
    private static String currentUserEmail;
    public void getUserFromDatabase(String u){
        currentUserEmail = u;
    }
    public static String getUser(){return currentUserEmail;}
}
