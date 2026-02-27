package com.mycontacts.auth;

import java.util.*;

import com.mycontacts.*;
import com.mycontacts.model.user.User;
import com.mycontacts.validation.Email;

public class UserDatabase {

    private static final Map<String, User> users = new HashMap<>();

    public static void addUser(User user) {
        if (user == null) return;
        Email emailObj = user.getEmail();
        if (emailObj == null) return;

        String key = normalize(emailObj.getValue());
        users.put(key, user);
    }

    public static boolean exists(String email) {
        return users.containsKey(normalize(email));
    }

    public static User findByEmail(String email) {
        return users.get(normalize(email));
    }

    public static Map<String, User> getUsers() {
        return users;
    }

    private static String normalize(String email) {
        return (email == null) ? null : email.trim().toLowerCase();
    }
}
