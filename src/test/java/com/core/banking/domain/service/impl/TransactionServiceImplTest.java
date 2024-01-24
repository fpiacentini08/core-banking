package com.core.banking.domain.service.impl;

import com.core.banking.domain.dto.transaction.TransactionDTO;
import com.core.banking.domain.enums.TransactionStatusEnum;
import com.core.banking.domain.enums.TransactionTypeEnum;
import com.core.banking.domain.model.Transaction;
import com.core.banking.domain.repository.TransactionRepository;
import com.core.banking.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransactionServiceImplTest
{
	@InjectMocks
	TransactionServiceImpl transactionService;

	@Mock
	TransactionRepository transactionRepository;

	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void givenId_whenGetTransaction_thenGetTransactionAndReturnTransactionDTO()
	{
		final String id = "123456";
		var fixedTransactionDTO = TransactionDTO.builder().id(id).accountId("789456").amount(BigDecimal.valueOf(13))
				.type("DEPOSIT").status("APPROVED").createdAt(
						LocalDateTime.MAX).updatedAt(LocalDateTime.MAX).build();
		var expectedTransaction = Transaction.builder().id(id).accountId("789456").amount(BigDecimal.valueOf(13))
				.type(TransactionTypeEnum.DEPOSIT).status(TransactionStatusEnum.APPROVED).createdAt(
						LocalDateTime.MAX).updatedAt(LocalDateTime.MAX).build();
		when(transactionRepository.findById(id)).thenReturn(Optional.of(expectedTransaction));
		var accountDTOResponse = transactionService.get(id);
		assertNotNull(accountDTOResponse);
		assertEquals(fixedTransactionDTO, accountDTOResponse);
		verify(transactionRepository, times(1)).findById(id);
	}

	@Test
	void givenIdThatDoesNotExist_whenGetTransaction_thenGetTransactionAndThrowException()
	{
		final String id = "123456";
		when(transactionRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> transactionService.get(id));
	}

}
