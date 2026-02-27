package com.mycontacts.model.user;

import com.mycontacts.validation.*;

public class FreeUser extends User {
	public FreeUser(Email email,String pwHash,String name) {
		super(email,pwHash,name);
	}
}