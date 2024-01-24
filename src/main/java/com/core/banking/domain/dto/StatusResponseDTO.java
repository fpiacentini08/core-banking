package com.core.banking.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record StatusResponseDTO(String code, String message)
{
}
