package com.core.banking.rest;

import com.core.banking.domain.dto.account.AccountCreationDTO;
import com.core.banking.domain.dto.transaction.ExecuteTransactionDTO;
import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.enums.TransactionTypeEnum;
import com.core.banking.domain.service.impl.ExecuteTransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExecuteTransactionControllerTest
{
	@InjectMocks
	ExecuteTransactionController executeTransactionController;

	@Mock
	ExecuteTransactionServiceImpl executeTransactionService;

	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	private static TransactionDTO defaultTransactionDTO()
	{
		return TransactionDTO.builder().id("123456").accountId("789456").amount(BigDecimal.valueOf(13))
				.type("DEPOSIT").status("APPROVED").createdAt(
						LocalDateTime.MAX).updatedAt(LocalDateTime.MAX).build();
	}

	@Test
	void givenExecuteTransactionDTO_whenCallExecuteTransaction_thenCallServiceAndReturnTransactionDTO()
	{
		final var executeTransactionDTO = ExecuteTransactionDTO.builder().type(TransactionTypeEnum.DEPOSIT)
				.accountId("123456").amount(BigDecimal.valueOf(10)).build();
		when(executeTransactionService.execute(any())).thenReturn(defaultTransactionDTO());
		final var transactionDTO = executeTransactionController.executeTransaction(executeTransactionDTO);
		assertNotNull(transactionDTO);
		assertEquals(defaultTransactionDTO(), transactionDTO);
		verify(executeTransactionService, times(1)).execute(executeTransactionDTO);
	}

}
