package com.core.banking.domain.service.impl;

import com.core.banking.domain.converter.TransactionToTransactionDTOConverter;
import com.core.banking.domain.dto.transaction.ExecuteTransactionDTO;
import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.enums.TransactionStatusEnum;
import com.core.banking.domain.model.Transaction;
import com.core.banking.domain.repository.TransactionRepository;
import com.core.banking.domain.service.AccountService;
import com.core.banking.domain.service.ExecuteTransactionService;
import com.core.banking.utils.LockByKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.locks.Lock;

@Service
public class ExecuteTransactionServiceImpl implements ExecuteTransactionService
{
	@Autowired
	AccountService accountService;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	LockByKey lockByKey;

	@Override
	public TransactionDTO execute(ExecuteTransactionDTO executeTransactionDTO)
	{
		Lock lock = lockByKey.getLock(executeTransactionDTO.accountId());
		lock.lock();
		switch (executeTransactionDTO.type())
		{
			case DEPOSIT -> executeDeposit(executeTransactionDTO);
			case WITHDRAW -> executeWithdraw(executeTransactionDTO);
		}
		lock.unlock();
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
				.status(TransactionStatusEnum.APPROVED.name())
				.type(executeTransactionDTO.type().name())
				.id(UUID.randomUUID().toString())
				.build();
		var savedTransaction = transactionRepository.save(transaction);
		return TransactionToTransactionDTOConverter.convert(savedTransaction);

	}

}
