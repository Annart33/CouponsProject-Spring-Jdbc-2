package com.anna.coupons.beans;

import com.anna.coupons.enums.UserType;

public class UserLoginData {

	private String email;
	private String password;
	private UserType userType;

	public UserLoginData(String email, String password, UserType userType) {
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	public UserLoginData() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserLoginData [email=" + email + ", password=" + password + ", userType=" + userType + "]";
	}
	
	


}
