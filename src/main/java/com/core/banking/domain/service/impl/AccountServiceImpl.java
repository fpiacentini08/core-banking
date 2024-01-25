package com.core.banking.domain.service.impl;

import com.core.banking.domain.converter.AccountToAccountDTOConverter;
import com.core.banking.domain.dto.account.AccountCreationDTO;
import com.core.banking.domain.dto.account.AccountDTO;
import com.core.banking.domain.enums.AccountStatusEnum;
import com.core.banking.domain.model.Account;
import com.core.banking.domain.repository.AccountRepository;
import com.core.banking.domain.service.AccountService;
import com.core.banking.exception.NotFoundException;
import com.core.banking.exception.codes.ErrorCodesList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

import static com.core.banking.exception.codes.ErrorCodesList.ACCOUNT_NOT_FOUND;

@Service
public class AccountServiceImpl implements AccountService
{
	@Autowired
	private AccountRepository accountRepository;

	private static Account initializeAccount(AccountCreationDTO accountCreationDTO)
	{
		return Account.builder().id(UUID.randomUUID().toString()).balance(BigDecimal.ZERO)
				.type(accountCreationDTO.type()).status(
						AccountStatusEnum.OPEN.name()).build();
	}

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
			throw new NotFoundException(ACCOUNT_NOT_FOUND);
		}
		return AccountToAccountDTOConverter.convert(accountOptional.get());
	}

	@Override
	@Transactional
	public void deposit(String id, BigDecimal amount)
	{
		var accountDTO = get(id);
		BigDecimal newBalance = accountDTO.balance().add(amount);
		accountRepository.updateAccountBalance(id, newBalance);
	}

	@Override
	@Transactional
	public void withdraw(String id, BigDecimal amount)
	{
		var accountDTO = get(id);
		BigDecimal newBalance = accountDTO.balance().subtract(amount);
		accountRepository.updateAccountBalance(id, newBalance);
	}

}
