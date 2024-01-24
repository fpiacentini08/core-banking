package com.core.banking.domain.service.impl;

import com.core.banking.domain.dto.AccountDTO;
import com.core.banking.domain.enums.AccountStatusEnum;
import com.core.banking.domain.model.Account;
import com.core.banking.domain.repository.AccountRepository;
import com.core.banking.domain.service.AccountService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountServiceImpl implements AccountService
{
	@Autowired
	AccountRepository accountRepository;
	private final ObjectMapper mapper = new ObjectMapper();

	@PostConstruct
	private void init(){
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	@Override
	public AccountDTO create(String type)
	{
		var newAccount = initializeAccount(type);
		accountRepository.save(newAccount);
		return mapper.convertValue(newAccount, AccountDTO.class);
	}

	private static Account initializeAccount(String type)
	{
		return Account.builder().id(UUID.randomUUID().toString()).balance(BigDecimal.ZERO).type(type).status(
				AccountStatusEnum.OPEN.name()).build();
	}
}
