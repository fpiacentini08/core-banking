package com.core.banking.exception;

import com.core.banking.exception.codes.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException
{
	private ErrorCode errorCode;
	public NotFoundException(ErrorCode errorCode){
		super(errorCode.message());
		this.errorCode = errorCode;
	}

}
