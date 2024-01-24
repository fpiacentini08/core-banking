package com.core.banking.rest;

import com.core.banking.domain.dto.transaction.ExecuteTransactionDTO;
import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.service.ExecuteTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/execute/transactions")
@RequiredArgsConstructor
public class ExecuteTransactionController
{

	@Autowired
	ExecuteTransactionService executeTransactionService;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public TransactionDTO executeTransaction(@RequestBody ExecuteTransactionDTO executeTransactionDTO)
	{
		return executeTransactionService.execute(executeTransactionDTO);
	}
}
