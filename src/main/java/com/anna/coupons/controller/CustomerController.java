package com.anna.coupons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.anna.coupons.beans.Customer;
import com.anna.coupons.dao.CustomerDao;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.validationUtils;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDao customerDao;

	public void createCustomer(Customer customer) throws ApplicationException {
		validateCreateCustomer(customer);

		this.customerDao.createCustomer(customer);
	}

	private void validateCreateCustomer(Customer customer) throws ApplicationException {
		if (this.customerDao.isCustomerExistByEmail(customer.getCustomerEmail())) {
			throw new ApplicationException(ErrorType.ALREADY_EXISTS,
					"The user attempted to create a new customer using a name that already exists.");
		}
		if (!validationUtils.ispasswordValid(customer.getCustomerPassword())) {
			throw new ApplicationException(ErrorType.INVALID_PASSWROD, "This password is invalid");
		}
		if (!validationUtils.isEmailAddressValid(customer.getCustomerEmail())) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL, "This email already exists");
		}
	}

	public Customer getCustomer(long customerId) throws ApplicationException {
		return this.customerDao.getCustomer(customerId);
	}

	public void deleteCustomer(long customerId) throws ApplicationException {
		this.customerDao.deleteCustomer(customerId);
	}

	public void updateCustomer(Customer customer) throws ApplicationException {
		this.customerDao.updateCustomer(customer);
	}

	public List<Customer> getAllCustomers() throws ApplicationException {
		return this.customerDao.getAllCustomers();
	}

	public Long login(String customerEmail, String customerPassword) throws ApplicationException {
		return customerDao.login(customerEmail, customerPassword);
	}

	public boolean isCustomerExistByEmail(String customerEmail) throws ApplicationException {
		return customerDao.isCustomerExistByEmail(customerEmail);
	}

	public boolean isCustomerExistByName(String customerName) throws ApplicationException {
		return customerDao.isCustomerExistByName(customerName);
	}
}
