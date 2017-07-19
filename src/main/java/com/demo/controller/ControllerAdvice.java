package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.exception.ServiceApplicationException;
import com.demo.vo.ResponseVO;

@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = "com.demo.controller")
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ServiceApplicationException.class, RuntimeException.class })
	@ResponseBody
	ResponseEntity<?> handleControllerException(Exception ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = "Internal Server Error";
		if (ex instanceof ServiceApplicationException) {
			status = ((ServiceApplicationException) ex).getHttpStatus();
			message = ex.getMessage();
		}
		return new ResponseEntity<>(new ResponseVO(status.value(), message), status);
	}
}
