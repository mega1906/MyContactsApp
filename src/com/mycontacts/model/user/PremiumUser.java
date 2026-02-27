package com.mycontacts.model.user;

import com.mycontacts.validation.*;

public class PremiumUser extends User{
	public PremiumUser(Email email,String pwHash,String name) {
		super(email,pwHash,name);
	}
}
