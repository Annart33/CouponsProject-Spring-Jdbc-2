package com.anna.coupons.utils;


public class validationUtils {

	public static boolean isEmailAddressValid(String emailAddress) {
//		String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\." + "[a-zA-Z0-9_+&-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
//				+ "A-Z]{2,7}$";
//
//		Pattern pat = Pattern.compile(emailRegex);
		
		if (emailAddress.contains("@")) {
			return true;
		}
		
		else { 
		return false;
		}

		//return pat.matcher(emailAddress).matches();
	}

	public static boolean ispasswordValid(String password) {
		if (password.length() >= 8) {
			return true;
		}
		return false;

	}
}