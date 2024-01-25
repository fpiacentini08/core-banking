package com.core.banking.rest.advice;

import com.core.banking.domain.dto.StatusResponseDTO;
import com.core.banking.exception.BadRequestException;
import com.core.banking.exception.NotFoundException;
import com.core.banking.exception.codes.ErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler
{

	@ExceptionHandler(value
			= { NotFoundException.class })
	protected ResponseEntity<Object> handleConflict(
			NotFoundException ex, WebRequest request)
	{
		StatusResponseDTO statusResponseDTO = buildStatusResponseDTOFromErrorCode(ex.getErrorCode());
		return handleExceptionInternal(ex, statusResponseDTO,
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}


	@ExceptionHandler(value
			= { BadRequestException.class })
	protected ResponseEntity<Object> handleConflict(
			BadRequestException ex, WebRequest request)
	{
		StatusResponseDTO statusResponseDTO = buildStatusResponseDTOFromErrorCode(ex.getErrorCode());
		return handleExceptionInternal(ex, statusResponseDTO,
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private static StatusResponseDTO buildStatusResponseDTOFromErrorCode(ErrorCode errorCode)
	{
		return StatusResponseDTO.builder().code(errorCode.code()).message(errorCode.message()).build();
	}
}
