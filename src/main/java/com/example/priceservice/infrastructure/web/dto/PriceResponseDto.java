package com.example.priceservice.infrastructure.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponseDto(
        long productId,
        long brandId,
        int priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        String currency)
{ }
