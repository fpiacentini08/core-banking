package com.core.banking.domain.service.impl;

import com.core.banking.domain.converter.TransactionToTransactionDTOConverter;
import com.core.banking.domain.dto.transaction.ExecuteTransactionDTO;
import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.enums.TransactionStatusEnum;
import com.core.banking.domain.model.Transaction;
import com.core.banking.domain.repository.TransactionRepository;
import com.core.banking.domain.service.AccountService;
import com.core.banking.domain.service.ExecuteTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExecuteTransactionServiceImpl implements ExecuteTransactionService
{
	@Autowired
	AccountService accountService;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public TransactionDTO execute(ExecuteTransactionDTO executeTransactionDTO)
	{
		switch (executeTransactionDTO.type())
		{
			case DEPOSIT -> executeDeposit(executeTransactionDTO);
			case WITHDRAW -> executeWithdraw(executeTransactionDTO);
		}
		return registerTransaction(executeTransactionDTO);
	}

	private void executeDeposit(ExecuteTransactionDTO executeTransactionDTO)
	{
		accountService.deposit(executeTransactionDTO.accountId(), executeTransactionDTO.amount());
	}

	private void executeWithdraw(ExecuteTransactionDTO executeTransactionDTO)
	{
		accountService.withdraw(executeTransactionDTO.accountId(), executeTransactionDTO.amount());
	}

	private TransactionDTO registerTransaction(ExecuteTransactionDTO executeTransactionDTO)
	{
		var transaction = Transaction.builder()
				.accountId(executeTransactionDTO.accountId())
				.amount(executeTransactionDTO.amount())
				.status(TransactionStatusEnum.APPROVED)
				.type(executeTransactionDTO.type())
				.id(UUID.randomUUID().toString())
				.build();
		var savedTransaction = transactionRepository.save(transaction);
		return TransactionToTransactionDTOConverter.convert(savedTransaction);

	}

}
