package com.mycontacts.contact;

import java.util.*;

public class ContactService {

    public List<Contacts> getDummyContacts() {
        List<Contacts> contacts = new ArrayList<>();
        contacts.add(new Contacts("TA", "9876543210"));
        contacts.add(new Contacts("SS", "9123456780"));
        contacts.add(new Contacts("VG", "9001122334"));
        contacts.add(new Contacts("SP", "9090909090"));
        return contacts;
    }
}