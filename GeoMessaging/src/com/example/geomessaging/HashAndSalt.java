package com.example.geomessaging;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashAndSalt {

	// mehr oder weniger zufaelliger Wert
	private static String salt = "kjashkajdshjkasdsdfsdfdsjfjsdfhj";

	public static String createMd5Hash(String input) {
		String md5 = null;
		if (null == input)
			return null;
		try {
			// salt wird an das Passwort dran gehauen
			input = input + salt;
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5.substring(0, 20);
	}
}
