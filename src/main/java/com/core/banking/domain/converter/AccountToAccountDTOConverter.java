package com.core.banking.domain.converter;

import com.core.banking.domain.dto.account.AccountDTO;
import com.core.banking.domain.model.Account;

public class AccountToAccountDTOConverter
{

	public static AccountDTO convert(Account account)
	{
		return AccountDTO.builder().id(account.getId()).status(account.getStatus()).balance(account.getBalance())
				.type(account.getType())
				.build();
	}
}
