package com.core.banking.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LockByKeyDistributedTest
{
	@InjectMocks
	LockByKeyDistributed lockByKeyDistributed;

	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void givenLockByKeyDistributed_whenGetLock_thenReturnLock()
	{
		var lock = lockByKeyDistributed.getLock("A");
		assertNotNull(lock);
	}
}
