package com.core.banking.rest;

import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionController
{

	@Autowired
	TransactionService transactionService;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TransactionDTO getTransaction(@PathVariable String id)
	{
		return transactionService.get(id);
	}
}
