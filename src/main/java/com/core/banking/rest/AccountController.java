package com.core.banking.rest;

import com.core.banking.domain.dto.account.AccountCreationDTO;
import com.core.banking.domain.dto.account.AccountDTO;
import com.core.banking.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController
{

	@Autowired
	AccountService accountService;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountDTO createAccount(@RequestBody AccountCreationDTO accountCreationDTO) {
		return accountService.create(accountCreationDTO);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDTO getAccount(@PathVariable String id) {
		return accountService.get(id);
	}
}
