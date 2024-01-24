package com.core.banking.domain.model;

import com.core.banking.domain.enums.TransactionStatusEnum;
import com.core.banking.domain.enums.TransactionTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transaction")
public class Transaction
{
	@Id
	private String id;
	private String accountId;
	private BigDecimal amount;
	private TransactionTypeEnum type;
	private TransactionStatusEnum status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@PrePersist
	public void onPrePersist()
	{
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void onPreUpdate(){
		this.updatedAt = LocalDateTime.now();
	}
}
