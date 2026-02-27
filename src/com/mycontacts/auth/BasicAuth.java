package com.mycontacts.auth;

import java.util.Map;

import com.mycontacts.*;
import com.mycontacts.model.user.User;
import com.mycontacts.validation.PasswordHashing; 

public class BasicAuth implements Authentication {

    private final Map<String, User> userStore;

    public BasicAuth(Map<String, User> userStore) {
        this.userStore = userStore;
    }

    @Override
    public boolean login(String email, String password) throws AuthException {
        if (email == null || password == null || email.isBlank() || password.isBlank()) {
            throw new AuthException("Email and password are required.");
        }

        String key = normalize(email);
        User user = userStore.get(key);
        if (user == null) {
            throw new AuthException("User not found.");
        }

        String hashed = PasswordHashing.hash(password); 
        if (!hashed.equals(user.getPwHash())) {
            throw new AuthException("Invalid password.");
        }

        return true;
    }

    private static String normalize(String email) {
        return email.trim().toLowerCase();
    }
}