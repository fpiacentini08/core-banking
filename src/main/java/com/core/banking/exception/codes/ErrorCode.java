package com.core.banking.exception.codes;

import lombok.Builder;

@Builder
public record ErrorCode(String code, String message)
{

}
