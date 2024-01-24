package com.core.banking.domain.service.impl;

import com.core.banking.domain.dto.AccountCreationDTO;
import com.core.banking.domain.dto.AccountDTO;
import com.core.banking.domain.model.Account;
import com.core.banking.domain.repository.AccountRepository;
import com.core.banking.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void givenAccountType_whenCreateAccount_thenCreateAccountAndReturnAccountDTO()
	{
		final String type = "CHECKING";
		when(accountRepository.save(any())).thenReturn(Account.builder().build());
		var account = accountService.create(AccountCreationDTO.builder().type("CHECKING").build());
		assertNotNull(account);
		assertNotNull(account.id());
		assertEquals(type, account.type());
		assertEquals(BigDecimal.ZERO, account.balance());
		assertEquals("OPEN", account.status());
		verify(accountRepository, times(1)).save(any());
	}

	@Test
	void givenId_whenGetAccount_thenGetAccountAndReturnAccountDTO()
	{
		final String id = "123456";
		var fixedAccountDTO = AccountDTO.builder().id(id).balance(BigDecimal.valueOf(10)).type("CHECKING")
				.status("CLOSED").build();
		var expectedAccount = Account.builder().id(id).balance(BigDecimal.valueOf(10)).type("CHECKING").status("CLOSED")
				.build();
		when(accountRepository.findById(id)).thenReturn(Optional.of(expectedAccount));
		var accountDTOResponse = accountService.get(id);
		assertNotNull(accountDTOResponse);
		assertEquals(fixedAccountDTO, accountDTOResponse);
		verify(accountRepository, times(1)).findById(id);
	}

	@Test
	void givenIdThatDoesNotExist_whenGetAccount_thenGetAccountAndThrowException()
	{
		final String id = "123456";
		when(accountRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class , () -> accountService.get(id));
	}

}
