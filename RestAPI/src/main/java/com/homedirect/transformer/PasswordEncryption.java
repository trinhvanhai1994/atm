package com.homedirect.transformer;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {
	public static String encryp(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
}
