package com.core.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class BankingApplication {

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("com.mysql.cj.jdbc.Driver")
				.url("jdbc:mysql://127.0.0.1:3306/lmb")
				.username("root")
				.password("1Qaz2wsx--")
				.build();

	}

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

}
