package com.core.banking.domain.dto.account;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountCreationDTO(String type)
{
}
