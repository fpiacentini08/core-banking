package com.core.banking.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Builder
public
record Account(@Id String id, String type, String status, BigDecimal balance){}

