package com.core.banking.domain.service;

import com.core.banking.domain.dto.account.AccountCreationDTO;
import com.core.banking.domain.dto.account.AccountDTO;

import java.math.BigDecimal;

public interface AccountService
{
	AccountDTO create(AccountCreationDTO accountCreationDTO);

	AccountDTO get(String id);

	void deposit(String id, BigDecimal amount);
	void withdraw(String id, BigDecimal amount);
}
