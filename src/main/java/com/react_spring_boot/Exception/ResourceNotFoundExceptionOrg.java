package com.react_spring_boot.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptionOrg extends RuntimeException{

	private static final int serialVersionUID = 1;

	public ResourceNotFoundExceptionOrg(String message) {
		super(message);
	}
}
