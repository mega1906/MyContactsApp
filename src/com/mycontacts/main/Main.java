/*
 * Use Case 2 - User Authentication
 * 
 * UC-02 (Login & Sessions) interactive console.
 * Adds Login, WhoAmI (session inspection) and Logout to the UC-01 registration flow.
 *
 * UserService in this console build exposes login/getSession/logout for convenience while delegating to the Authentication strategy and SessionManager internally
 *
 *  @author Developer
 *  @version 2.0
 */
package com.mycontacts.main;

import java.util.List;
import java.util.Scanner;

import com.mycontacts.*;
import com.mycontacts.auth.AuthException;
import com.mycontacts.auth.Authentication;
import com.mycontacts.auth.BasicAuth;
import com.mycontacts.auth.UserDatabase;
import com.mycontacts.contact.ContactService;
import com.mycontacts.contact.Contacts;
import com.mycontacts.model.user.FreeUser;
import com.mycontacts.model.user.PremiumUser;
import com.mycontacts.model.user.User;
import com.mycontacts.validation.Email;
import com.mycontacts.validation.PasswordHashing;
import com.mycontacts.validation.PasswordValidation;
import com.mycontacts.validation.UserException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Authentication auth = new BasicAuth(UserDatabase.getUsers());

        while (true) {
            System.out.println("\nMy Contact App\n");
            System.out.println("1) Register");
            System.out.println("2) Login");
            System.out.println("3) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    handleRegistration(sc);
                    break;
                case "2":
                    handleLogin(sc, auth);
                    break;
                case "3":
                    System.out.println("Exit Successfull!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }
    
    // UC 01: Registration flow
    private static void handleRegistration(Scanner sc) {
        System.out.println("\nRegistration");

        try {
            // Ask inputs
            System.out.print("Enter user type (free/premium): ");
            String type = sc.nextLine().trim();

            System.out.print("Enter email: ");
            String emailRaw = sc.nextLine().trim();

            System.out.print("Enter name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter password: ");
            String passwordRaw = sc.nextLine();

            // Validate email + password
            Email email = new Email(emailRaw);
            PasswordValidation.validate(passwordRaw);
            String pwHash = PasswordHashing.hash(passwordRaw);

            // Build user type 
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

            // Store in UC 02 DB for later login
            UserDatabase.addUser(user);

            System.out.println("User registered: " + user);

        } catch (UserException ue) {
            // Your validation or constructor errors
            System.out.println("Registration failed: " + ue.getMessage());
        } 
        catch (IllegalArgumentException iae) {
            // Email invalid format
            System.out.println("Registration failed: " + iae.getMessage());
        } 
        catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    // UC 02: Login + Contacts
    private static void handleLogin(Scanner sc, Authentication auth) {
        System.out.println("\nLogin");

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine();

        try {
            if (auth.login(email, password)) {
                System.out.println("Login successful.");
                showContacts();
            }
        } 
        catch (AuthException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    private static void showContacts() {
        ContactService cs = new ContactService();
        List<Contacts> contacts = cs.getDummyContacts();

        System.out.println("\nYour Contact List:");
        for (Contacts c : contacts) {
            System.out.println(c.getName() + " - " + c.getPhone());
        }
    }
}