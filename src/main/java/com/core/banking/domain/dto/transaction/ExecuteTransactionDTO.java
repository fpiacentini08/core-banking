package com.core.banking.domain.dto.transaction;

import com.core.banking.domain.enums.TransactionTypeEnum;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ExecuteTransactionDTO(String accountId, TransactionTypeEnum type, BigDecimal amount)
{
}
