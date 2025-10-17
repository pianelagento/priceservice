package com.example.priceservice.domain.repository;

import com.example.priceservice.domain.model.Price;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

public interface PricePort {
    Mono<Price> findApplicablePrice(long brandId, long productId, LocalDateTime applicationDate);
}
