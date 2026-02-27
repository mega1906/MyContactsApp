package com.mycontacts.main;

import com.mycontacts.model.user.FreeUser;
import com.mycontacts.model.user.PremiumUser;
import com.mycontacts.model.user.User;
import com.mycontacts.validation.Email;
import com.mycontacts.validation.PasswordHashing;
import com.mycontacts.validation.PasswordValidation;
import com.mycontacts.validation.UserException;

public class Main{

    public static void main(String[] args) {

        try {
           
            String type = "premium";    
            String emailRaw = "mega@example.com";
            String passwordRaw = "Mega12345&";
            String name = "Mega";
            
            Email email = new Email(emailRaw);
            PasswordValidation.validate(passwordRaw);
            String pwHash = PasswordHashing.hash(passwordRaw);

            User user;

            if (type.equalsIgnoreCase("free")) {
                user = new FreeUser(email, pwHash, name);
            }
            else if (type.equalsIgnoreCase("premium")) {
                user = new PremiumUser(email, pwHash, name);
            }
            else {
                throw new UserException("Invalid user type: " + type);
            }

            System.out.println("User registered: " + user);

        } catch (UserException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }
}
