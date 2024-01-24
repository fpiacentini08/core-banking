package com.core.banking.domain.service.impl;

import com.core.banking.domain.converter.AccountToAccountDTOConverter;
import com.core.banking.domain.dto.account.AccountCreationDTO;
import com.core.banking.domain.dto.account.AccountDTO;
import com.core.banking.domain.enums.AccountStatusEnum;
import com.core.banking.domain.model.Account;
import com.core.banking.domain.repository.AccountRepository;
import com.core.banking.domain.service.AccountService;
import com.core.banking.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService
{
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountDTO create(AccountCreationDTO accountCreationDTO)
	{
		var newAccount = initializeAccount(accountCreationDTO);
		accountRepository.save(newAccount);
		return AccountToAccountDTOConverter.convert(newAccount);
	}

	@Override
	public AccountDTO get(String id)
	{
		var accountOptional = accountRepository.findById(id);
		if (accountOptional.isEmpty())
		{
			throw new NotFoundException("01", "Account not found");
		}
		return AccountToAccountDTOConverter.convert(accountOptional.get());
	}

	private static Account initializeAccount(AccountCreationDTO accountCreationDTO)
	{
		return Account.builder().id(UUID.randomUUID().toString()).balance(BigDecimal.ZERO)
				.type(accountCreationDTO.type()).status(
						AccountStatusEnum.OPEN.name()).build();
	}
}
