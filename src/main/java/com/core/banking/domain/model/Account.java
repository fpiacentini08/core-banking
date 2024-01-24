package com.core.banking.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
public class Account
{
	@Id
	private String id;
	private String type;
	private String status;
	private BigDecimal balance;

}

