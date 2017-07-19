package com.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServiceApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7844363272251158036L;
	protected HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	public ServiceApplicationException(String string) {
		super(string);
	}

	public ServiceApplicationException(HttpStatus httpStatus, String string) {
		super(string);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ResponseEntity<Object> getResponse() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", getMessage());
		return new ResponseEntity<Object>(map, httpStatus);
	}

	public static ResponseEntity<Object> buildResponse(Exception e) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "Internal Server Error.");
		return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
