package com.core.banking.domain.service;

import com.core.banking.domain.dto.AccountCreationDTO;
import com.core.banking.domain.dto.AccountDTO;

public interface AccountService
{
	public AccountDTO create(AccountCreationDTO accountCreationDTO);

}
