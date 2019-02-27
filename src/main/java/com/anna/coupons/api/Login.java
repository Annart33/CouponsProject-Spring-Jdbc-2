package com.anna.coupons.api;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anna.coupons.beans.IdCover;
import com.anna.coupons.beans.UserLoginData;
import com.anna.coupons.controller.CompanyController;
import com.anna.coupons.controller.CustomerController;
import com.anna.coupons.enums.UserType;
import com.anna.coupons.exceptions.ApplicationException;

@RestController
@RequestMapping(value = "/login")
public class Login {

	@Autowired
	private CompanyController companyController;

	@Autowired
	private CustomerController customerController;

	@PostMapping
	public String login(@RequestBody UserLoginData loginData, HttpServletResponse response, HttpServletRequest request)
			throws ApplicationException, JsonGenerationException, JsonMappingException, IOException {

		String email = loginData.getEmail();
		String password = loginData.getPassword();
		UserType userType = loginData.getUserType();
		Long id = null;

		if (userType == UserType.COMPANY) {
			id = companyController.login(email, password);
			request.getSession();
		} else if (userType == UserType.CUSTOMER) {
			id = customerController.login(email, password);
			request.getSession();
		}

		IdCover idCover = new IdCover(id);

		Cookie cookie = new Cookie("login", Long.toString(id));
		cookie.setPath("/");
		response.addCookie(cookie);

		return idCover.getId();
	}

}
