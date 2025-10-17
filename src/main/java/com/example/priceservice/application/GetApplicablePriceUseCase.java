package com.example.priceservice.application;

import com.example.priceservice.domain.model.Price;
import com.example.priceservice.domain.repository.PricePort;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
public class GetApplicablePriceUseCase {
    private final PricePort pricePort;

    public GetApplicablePriceUseCase(PricePort pricePort) {
        this.pricePort = pricePort;
    }

    public Mono<Price> execute(long brandId, long productId, LocalDateTime applicationDate) {
        return pricePort.findApplicablePrice(brandId, productId, applicationDate);
    }
}
