package com.core.banking.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException
{
	private String code;
	public BadRequestException(String code, String message){
		super(message);
		this.code = code;
	}

}
