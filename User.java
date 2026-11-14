package com.tripledigitaldaggers;
import java.util.HashSet;

public class User {
    //Given more time, this would be SQL
    private static HashSet<String> usernames = new HashSet<>();

    public static void addUsername(String user) {
        usernames.add(user);
    }

    public static boolean findUsername(String user) {
        return usernames.contains(user);
    }
    
}
