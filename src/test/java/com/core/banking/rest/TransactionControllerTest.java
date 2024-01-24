package com.core.banking.rest;

import com.core.banking.domain.dto.account.AccountDTO;
import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.service.impl.TransactionServiceImpl;
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

public class TransactionControllerTest
{
	@InjectMocks
	TransactionController transactionController;

	@Mock
	TransactionServiceImpl transactionService;

	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	private static TransactionDTO defaultTransactionDTO()
	{
		return TransactionDTO.builder().id("123456").accountId("789456").amount(BigDecimal.valueOf(13))
				.type("DEPOSIT").createdAt(
						LocalDateTime.MAX).updatedAt(LocalDateTime.MAX).build();
	}


	@Test
	void givenId_whenCallGetTransaction_thenCallServiceAndReturnTransaction()
	{
		when(transactionService.get("123456")).thenReturn(defaultTransactionDTO());
		var account = transactionController.getTransaction("123456");
		assertNotNull(account);
		assertEquals(defaultTransactionDTO(), account);
		verify(transactionService, times(1)).get("123456");
	}

}
