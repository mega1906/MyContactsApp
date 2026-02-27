package com.mycontacts.auth;

public interface Authentication {
    boolean login(String email, String password) throws AuthException;
}
