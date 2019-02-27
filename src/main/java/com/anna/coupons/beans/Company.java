package com.anna.coupons.beans;



import com.anna.coupons.enums.UserType;

public class Company {

	private long companyId;
	private String companyName;
	private String companyPassword;
	private String companyEmail;
	private UserType COMPANY;

	public Company() {
	}

	public Company(long companyId, String companyName, String companyPassword, String companyEmail) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyPassword = companyPassword;
		this.companyEmail = companyEmail;
	}

	public Company(String companyName, String companyPassword, String companyEmail) {
		this.companyName = companyName;
		this.companyPassword = companyPassword;
		this.companyEmail = companyEmail;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long l) {
		this.companyId = l;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPassword() {
		return companyPassword;
	}

	public void setCompanyPassword(String companyPassword) {
		this.companyPassword = companyPassword;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	@Override
	public String toString() {
		return "\n [Company id : " + companyId + ", Company name : " + companyName + ", Company password : "
				+ companyPassword + ", Company email : " + companyEmail + "]";

	}

	public UserType getCOMPANY() {
		return COMPANY;
	}

}
