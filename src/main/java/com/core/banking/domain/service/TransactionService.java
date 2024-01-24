package com.core.banking.domain.service;

import com.core.banking.domain.dto.transaction.TransactionDTO;

public interface TransactionService
{
	TransactionDTO get(String id);
}
