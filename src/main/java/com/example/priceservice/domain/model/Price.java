package com.example.priceservice.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record  Price (
        long brandId,
        long productId,
        int priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        int priority,
        BigDecimal price,
        String currency
) { }
