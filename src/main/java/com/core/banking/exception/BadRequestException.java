package com.core.banking.exception;

import com.core.banking.exception.codes.ErrorCode;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException
{
	private ErrorCode errorCode;
	public BadRequestException(ErrorCode errorCode){
		super(errorCode.message());
		this.errorCode = errorCode;
	}

}
