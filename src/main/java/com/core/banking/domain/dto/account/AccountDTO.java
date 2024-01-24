package com.core.banking.domain.dto.account;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountDTO(String id, String type, String status, BigDecimal balance)
{
}
