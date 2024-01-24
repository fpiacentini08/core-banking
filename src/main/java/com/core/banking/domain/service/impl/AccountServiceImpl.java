package com.core.banking.domain.service.impl;

import com.core.banking.domain.converter.AccountToAccountDTOConverter;
import com.core.banking.domain.dto.AccountCreationDTO;
import com.core.banking.domain.dto.AccountDTO;
import com.core.banking.domain.enums.AccountStatusEnum;
import com.core.banking.domain.model.Account;
import com.core.banking.domain.repository.AccountRepository;
import com.core.banking.domain.service.AccountService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService
{
	@Autowired
	AccountRepository accountRepository;

	@Override
	public AccountDTO create(AccountCreationDTO accountCreationDTO)
	{
		var newAccount = initializeAccount(accountCreationDTO);
		accountRepository.save(newAccount);
		return AccountToAccountDTOConverter.convert(newAccount);
	}

	private static Account initializeAccount(AccountCreationDTO accountCreationDTO)
	{
		return Account.builder().id(UUID.randomUUID().toString()).balance(BigDecimal.ZERO)
				.type(accountCreationDTO.type()).status(
						AccountStatusEnum.OPEN.name()).build();
	}
}
