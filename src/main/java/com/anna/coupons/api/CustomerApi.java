package com.anna.coupons.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anna.coupons.beans.Customer;
import com.anna.coupons.controller.CustomerController;
import com.anna.coupons.exceptions.ApplicationException;

@RestController
@RequestMapping("/customers")
public class CustomerApi {

	@Autowired
	private CustomerController customerController;


	@PostMapping
	public void createCustomer(@RequestBody Customer customer) throws ApplicationException {
		customerController.createCustomer(customer);

	}

	@GetMapping
	@RequestMapping("/getCustomer/{customerId}")
	public Customer getCustomer(@PathVariable("customerId") long customerId) throws ApplicationException {
		return customerController.getCustomer(customerId);
	}

	@DeleteMapping
	@RequestMapping("/{customerId}")
	public void deleteCustomer(@PathVariable("customerId") long customerId) throws ApplicationException {
		customerController.deleteCustomer(customerId);
	}

	@PutMapping
	public void updateCustomer(@RequestBody Customer customer) throws ApplicationException {
		customerController.updateCustomer(customer);
	}

	@GetMapping
	public List<Customer> getAllCustomers() throws ApplicationException {
		return customerController.getAllCustomers();
	}



}
