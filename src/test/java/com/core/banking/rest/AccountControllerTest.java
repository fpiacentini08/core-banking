package com.core.banking.rest;

import com.core.banking.domain.dto.AccountCreationDTO;
import com.core.banking.domain.dto.AccountDTO;
import com.core.banking.domain.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountControllerTest
{
	@InjectMocks
	AccountController accountController;

	@Mock
	AccountServiceImpl accountService;

	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	private static AccountDTO defaultAccountDTO()
	{
		return AccountDTO.builder().id("123456").balance(BigDecimal.ZERO).status("OPEN").type("CHECKING").build();
	}

	@Test
	void givenAccountType_whenCallCreateAccount_thenCallServiceAndReturnAccount()
	{
		when(accountService.create(any())).thenReturn(defaultAccountDTO());
		var account = accountController.createAccount(AccountCreationDTO.builder().type("CHECKING").build());
		assertNotNull(account);
		assertEquals(defaultAccountDTO(), account);
		verify(accountService, times(1)).create(any());
	}
}
