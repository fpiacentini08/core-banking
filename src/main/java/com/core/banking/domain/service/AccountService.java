package com.core.banking.domain.service;

import com.core.banking.domain.dto.AccountCreationDTO;
import com.core.banking.domain.dto.AccountDTO;

public interface AccountService
{
	AccountDTO create(AccountCreationDTO accountCreationDTO);

	AccountDTO get(String id);
}
