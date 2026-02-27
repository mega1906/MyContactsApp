
package com.mycontacts.validation;

public class PasswordValidation {

    public static void validate(String password) {
        if (password == null || password.length() < 8) {
            throw new UserException("Password must be at least 8 characters.");
        }
    }
}
