package com.example.priceservice.infrastructure.persistence.repository;

import com.example.priceservice.domain.model.Price;
import com.example.priceservice.domain.repository.PricePort;
import com.example.priceservice.domain.repository.PriceReactiveRepository;
import com.example.priceservice.infrastructure.persistence.mapper.PriceMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class PriceR2dbcRepository implements PricePort {
    private final PriceReactiveRepository repo;
    private final PriceMapper mapper;

    public PriceR2dbcRepository(PriceReactiveRepository repo, PriceMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Mono<Price> findApplicablePrice(long brandId, long productId, LocalDateTime applicationDate) {
        return repo.findTopApplicable(brandId, productId, applicationDate)
                .map(mapper::toDomain);
    }

}
