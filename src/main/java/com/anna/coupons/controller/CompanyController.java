package com.anna.coupons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.anna.coupons.beans.Company;
import com.anna.coupons.dao.CompanyDao;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.validationUtils;

@Controller
public class CompanyController {
	
	@Autowired
	private CompanyDao companyDao;

	public Long createCompany(Company company) throws ApplicationException {
		validateCreateCompany(company);

		return this.companyDao.createCompany(company);
	}

	private void validateCreateCompany(Company company) throws ApplicationException {

		if (!validationUtils.isEmailAddressValid(company.getCompanyEmail())) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL, "Not an Email format");
		} else if (!validationUtils.ispasswordValid(company.getCompanyPassword())) {
			throw new ApplicationException(ErrorType.INVALID_PASSWROD,
					"Password doesn't contain Upper-Case letters or 8 digits");
		}

	}

	public void deleteCompany(long companyId) throws ApplicationException {
		this.companyDao.deleteCompany(companyId);
	}

	public void updateCompany(Company company) throws ApplicationException {
		this.companyDao.updateCompany(company);
	}

	public List<Company> getAllCompanies() throws ApplicationException {
		return this.companyDao.getAllComapnies();
	}

	public Company getCompany(long companyId) throws ApplicationException {
		return this.companyDao.getCompany(companyId);
	}

	public Long login(String companyEmail, String companyPassword) throws ApplicationException {
		return this.companyDao.login(companyEmail, companyPassword);
	}
}
