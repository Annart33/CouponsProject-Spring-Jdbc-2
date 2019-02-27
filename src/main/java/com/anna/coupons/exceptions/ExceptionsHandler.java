package com.anna.coupons.exceptions;


import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anna.coupons.beans.ErrorBean;

@ResponseBody
@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(ApplicationException.class)
	public ErrorBean applicationExceptionHandler(HttpServletResponse response, ApplicationException applicationException ) {
		
		int errorCode = applicationException.getErrorType().getErrorNumber();
		String internalMessage = applicationException.getMessage();
		String errorMessage = applicationException.getErrorType().getErrorMessage();
		ErrorBean errorBean = new ErrorBean (errorCode, internalMessage, errorMessage);
		
		response.setStatus(applicationException.getErrorType().getErrorNumber());
		applicationException.printStackTrace();
		return errorBean;
		
	}
	
	@ExceptionHandler({Exception.class, Error.class})
	public ErrorBean exceptionHandler(HttpServletResponse response, Throwable exception) {

		int errorCode = 601;
		String internalMessage = exception.getMessage();
		ErrorBean errorBean = new ErrorBean (601, internalMessage, "General Error");
		exception.printStackTrace();
		response.setStatus(errorCode);
		return errorBean;
		
	}
	}


