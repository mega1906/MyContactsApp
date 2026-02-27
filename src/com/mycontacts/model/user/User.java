package com.mycontacts.model.user;

import com.mycontacts.validation.*;

public abstract class User {
	private final Email email;
	private final String pwHash;
	private final String name;
	public User(Email email, String pwHash, String name) {
		this.email=email;
		this.pwHash=pwHash;
		this.name=name;
	}
	public Email getEmail() {
		return email;
	}
	public String getPwHash() {
		return pwHash;
	}
	public String getName() {
		return name;
	}

@Override
    public String toString() {
        return getClass().getSimpleName() +
                " {email=" + email.getValue() + ", name=" + name + "}";
    }

}