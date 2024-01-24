package com.core.banking.domain.service;

import com.core.banking.domain.dto.account.AccountCreationDTO;
import com.core.banking.domain.dto.account.AccountDTO;

public interface AccountService
{
	AccountDTO create(AccountCreationDTO accountCreationDTO);

	AccountDTO get(String id);
}
