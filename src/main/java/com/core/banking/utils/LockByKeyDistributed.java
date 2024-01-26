package com.core.banking.utils;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LockByKeyDistributed implements LockByKey
{

	private RedissonClient client;

	private void init()
	{
		if (client == null)
		{
			Config config = new Config();
			config.useSingleServer().setAddress("redis://localhost:6379");
			client = Redisson.create(config);
		}
	}

	@Override
	public Lock getLock(String key)
	{
		init();
		return client.getLock(key);
	}

}
