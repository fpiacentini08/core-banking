package com.core.banking.utils;

import java.util.concurrent.locks.Lock;

public interface LockByKey
{
	public Lock getLock(String key);
}