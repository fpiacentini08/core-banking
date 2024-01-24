package com.core.banking.domain.service;

import com.core.banking.domain.dto.transaction.ExecuteTransactionDTO;
import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface ExecuteTransactionService
{
	TransactionDTO execute(ExecuteTransactionDTO executeTransactionDTO);
}
