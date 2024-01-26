package com.core.banking.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseResourceTest
{
	@Test
	void givenDatasource_whenDatasource_thenReturnDatasource(){
		var databaseDatasource = new DatabaseDatasource();
		var datasource = databaseDatasource.dataSource();
		assertNotNull(datasource);
	}
}
