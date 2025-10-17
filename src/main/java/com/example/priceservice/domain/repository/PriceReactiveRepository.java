package com.example.priceservice.domain.repository;

import com.example.priceservice.domain.model.Price;
import com.example.priceservice.domain.repository.PricePort;
import com.example.priceservice.infrastructure.persistence.entity.PriceEntity;
import com.example.priceservice.infrastructure.persistence.mapper.PriceMapper;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
public interface PriceReactiveRepository extends ReactiveCrudRepository<PriceEntity, Long> {

    @Query("""
      SELECT * FROM PRICES
      WHERE brand_id = :brandId
        AND product_id = :productId
        AND :applicationDate >= start_date
        AND :applicationDate <= end_date
      ORDER BY priority DESC
      LIMIT 1
      """)
    Mono<PriceEntity> findTopApplicable(Long brandId, Long productId, LocalDateTime applicationDate);
}
