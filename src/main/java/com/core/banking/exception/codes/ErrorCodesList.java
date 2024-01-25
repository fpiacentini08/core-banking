package com.core.banking.exception.codes;

public class ErrorCodesList
{
	public static final ErrorCode ACCOUNT_NOT_FOUND = ErrorCode.builder().code("01").message("Account not found").build();
	public static final ErrorCode TRANSACTION_NOT_FOUND = ErrorCode.builder().code("02").message("Transaction not found").build();


}
