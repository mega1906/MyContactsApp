package com.mycontacts.validation;
import java.util.regex.*;
public class Email {
	private final String value;
	public static final String pattern="^[a-zA-Z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
	static final Pattern compiledpattern=Pattern.compile(pattern);
	public Email(String value) {
		if(value==null || !compiledpattern.matcher(value).matches()) {
			throw new IllegalArgumentException("Invalid email: " + value);
		}
		else {
			this.value=value;
		}
	}
	public String getValue() {
		return value;
	}
}


