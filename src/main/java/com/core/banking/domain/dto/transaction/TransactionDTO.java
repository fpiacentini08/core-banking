package com.core.banking.domain.dto.transaction;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransactionDTO(String id, String accountId, BigDecimal amount, String type, String status,
							 LocalDateTime createdAt, LocalDateTime updatedAt)
{
}
