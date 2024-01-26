package com.core.banking.domain.service.impl;

import com.core.banking.domain.dto.account.AccountCreationDTO;
import com.core.banking.domain.dto.transaction.ExecuteTransactionDTO;
import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.enums.TransactionStatusEnum;
import com.core.banking.domain.enums.TransactionTypeEnum;
import com.core.banking.domain.model.Transaction;
import com.core.banking.domain.repository.TransactionRepository;
import com.core.banking.utils.LockByKeyDistributed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExecuteTransactionServiceImplTest
{
	@InjectMocks
	ExecuteTransactionServiceImpl executeTransactionService;

	@Mock
	TransactionRepository transactionRepository;

	@Mock
	AccountServiceImpl accountService;

	@Mock
	LockByKeyDistributed lockByKey;

	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void givenExecuteTransactionDTOForDeposit_whenExecuteTransaction_thenExecuteAccountServiceDepositAndRegisterTransactionAndReturnTransactionDTO()
	{
		final var executeTransactionDTO = ExecuteTransactionDTO.builder().type(TransactionTypeEnum.DEPOSIT)
				.accountId("123456").amount(BigDecimal.valueOf(10)).build();

		final var expectedTransaction = Transaction.builder().type(TransactionTypeEnum.DEPOSIT.name()).id("789456")
				.amount(BigDecimal.valueOf(10)).accountId("123456")
				.status(TransactionStatusEnum.APPROVED.name()).createdAt(LocalDateTime.MAX).updatedAt(LocalDateTime.MAX)
				.build();
		final var expectedTransactionDTO = TransactionDTO.builder().type("DEPOSIT").id("789456")
				.amount(BigDecimal.valueOf(10)).accountId("123456")
				.status("APPROVED").createdAt(LocalDateTime.MAX).updatedAt(LocalDateTime.MAX)
				.build();

		doNothing().when(accountService).deposit(executeTransactionDTO.accountId(), executeTransactionDTO.amount());
		when(transactionRepository.save(any())).thenReturn(expectedTransaction);
		when(lockByKey.getLock(any())).thenReturn(mock(Lock.class));

		var transactionDTO = executeTransactionService.execute(executeTransactionDTO);
		assertNotNull(transactionDTO);
		assertEquals(expectedTransactionDTO, transactionDTO);
		verify(accountService, times(0)).withdraw(executeTransactionDTO.accountId(), executeTransactionDTO.amount());
		verify(accountService, times(1)).deposit(executeTransactionDTO.accountId(), executeTransactionDTO.amount());
		verify(transactionRepository, times(1)).save(any());
	}

	@Test
	void givenExecuteTransactionDTOForWithdraw_whenExecuteTransaction_thenExecuteAccountServiceDepositAndRegisterTransactionAndReturnTransactionDTO()
	{
		final var executeTransactionDTO = ExecuteTransactionDTO.builder().type(TransactionTypeEnum.WITHDRAW)
				.accountId("123456").amount(BigDecimal.valueOf(10)).build();

		final var expectedTransaction = Transaction.builder().type(TransactionTypeEnum.WITHDRAW.name()).id("789456")
				.amount(BigDecimal.valueOf(10)).accountId("123456")
				.status(TransactionStatusEnum.APPROVED.name()).createdAt(LocalDateTime.MAX).updatedAt(LocalDateTime.MAX)
				.build();
		final var expectedTransactionDTO = TransactionDTO.builder().type("WITHDRAW").id("789456")
				.amount(BigDecimal.valueOf(10)).accountId("123456")
				.status("APPROVED").createdAt(LocalDateTime.MAX).updatedAt(LocalDateTime.MAX)
				.build();

		doNothing().when(accountService).withdraw(executeTransactionDTO.accountId(), executeTransactionDTO.amount());
		when(transactionRepository.save(any())).thenReturn(expectedTransaction);
		when(lockByKey.getLock(any())).thenReturn(mock(Lock.class));

		var transactionDTO = executeTransactionService.execute(executeTransactionDTO);
		assertNotNull(transactionDTO);
		assertEquals(expectedTransactionDTO, transactionDTO);
		verify(accountService, times(1)).withdraw(executeTransactionDTO.accountId(), executeTransactionDTO.amount());
		verify(accountService, times(0)).deposit(executeTransactionDTO.accountId(), executeTransactionDTO.amount());
		verify(transactionRepository, times(1)).save(any());
	}

}
