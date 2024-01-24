package com.core.banking.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseDatasource
{
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("com.mysql.cj.jdbc.Driver")
				.url("jdbc:mysql://127.0.0.1:3306/core_banking")
				.username("root")
				.password("1Qaz2wsx--")
				.build();

	}
}
