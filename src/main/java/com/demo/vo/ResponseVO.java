package com.demo.vo;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

public class ResponseVO implements ErrorAttributes {

	private int status;
	private String message;
	private Object response = null;

	public ResponseVO() {
		super();
	}
	
	public ResponseVO(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
		this.status = HttpStatus.OK.value();
	}

	@Override
	public Throwable getError(WebRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
