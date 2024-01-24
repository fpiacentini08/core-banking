package com.core.banking.domain.service.impl;

import com.core.banking.domain.model.Account;
import com.core.banking.domain.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest
{
	@InjectMocks
	AccountServiceImpl accountService;

	@Mock
	AccountRepository accountRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void givenAccountType_whenCreateAccount_thenCreateAccountAndReturnAccountApi()
	{
		final String type = "CHECKING";
		when(accountRepository.save(any())).thenReturn(Account.builder().build());
		var account = accountService.create(type);
		assertNotNull(account);
		assertNotNull(account.id());
		assertEquals(type, account.type());
		assertEquals(BigDecimal.ZERO, account.balance());
		assertEquals("OPEN", account.status());
		verify(accountRepository, times(1)).save(any());

	}
}
