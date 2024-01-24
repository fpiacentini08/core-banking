package com.core.banking.domain.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Builder
@Getter
@Table("account")
public class Account implements Persistable<String>
{
	@Id
	private String id;
	private String type;
	private String status;
	private BigDecimal balance;

	@Transient
	@Builder.Default
	private boolean isNewEntry = true;

	@Override
	public String getId()
	{
		return id;
	}

	public boolean isNew() {
		return isNewEntry;
	}

}

