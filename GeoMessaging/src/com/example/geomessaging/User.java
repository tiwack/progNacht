package com.example.geomessaging;

public class User {
	private static String mail = null;
	public static boolean login(String name, String password){
		String hash = HashAndSalt.md5(password);
		mail = name;	
		return true;
	}
	public static boolean register(String name, String password){
		String hash = HashAndSalt.md5(password);
		return true;
	}
	public static String getMail(){
		return mail;
	}
	public static void deleteMail(){
		mail = null;
	}
	public static boolean regexMail(String mail){
		if (mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			return true;
			}
		return false;
	}
}