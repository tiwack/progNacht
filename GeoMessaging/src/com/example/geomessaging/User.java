package com.example.geomessaging;

import android.util.Log;

public class User {
	private static String mail = null;

	public static boolean login(String email, String password) {
		String direktive = "login.php";
		if (WebServerKommunikation.doPostAnfrage(email, password, direktive)) {
			mail = email;
			return true;
		}
		return false;
	}
 
	public static boolean register(String email, String password) {
		String direktive = "addUser.php";
		if (WebServerKommunikation.doPostAnfrage(email, password, direktive)) {
			mail = email;
			return true;
		}
		return false;

	}

	public static String getMail() {
		return mail;
	}

	public static void deleteMail() {
		// ausloggen durch NULL
		mail = null;
	}

	// ueberprueft ob Parameter gueltige Mailadresse ist
	public static boolean checkRegexMail(String mail) {
		if (mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			Log.i("regex-mail", "funktioniert");
			return true;
		}
		Log.i("regex-mail", "fail");
		return false;
	}

}