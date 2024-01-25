package com.core.banking.domain.service.impl;

import com.core.banking.domain.converter.AccountToAccountDTOConverter;
import com.core.banking.domain.converter.TransactionToTransactionDTOConverter;
import com.core.banking.domain.dto.account.AccountDTO;
import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.repository.TransactionRepository;
import com.core.banking.domain.service.TransactionService;
import com.core.banking.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.core.banking.exception.codes.ErrorCodesList.TRANSACTION_NOT_FOUND;

@Service
public class TransactionServiceImpl implements TransactionService
{
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public TransactionDTO get(String id)
	{
		var transactionOptional = transactionRepository.findById(id);
		if (transactionOptional.isEmpty())
		{
			throw new NotFoundException(TRANSACTION_NOT_FOUND);
		}
		return TransactionToTransactionDTOConverter.convert(transactionOptional.get());
	}
}
