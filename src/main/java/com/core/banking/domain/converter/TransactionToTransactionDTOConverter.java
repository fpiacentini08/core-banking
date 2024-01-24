package com.core.banking.domain.converter;

import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.model.Transaction;

public class TransactionToTransactionDTOConverter
{

	public static TransactionDTO convert(Transaction transaction)
	{
		return TransactionDTO.builder().id(transaction.getId())
				.amount(transaction.getAmount())
				.accountId(transaction.getAccountId())
				.type(transaction.getType())
				.createdAt(transaction.getCreatedAt())
				.updatedAt(transaction.getUpdatedAt())
				.build();
	}
}
