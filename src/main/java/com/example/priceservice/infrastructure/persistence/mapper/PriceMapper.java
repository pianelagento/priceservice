package com.example.priceservice.infrastructure.persistence.mapper;

import com.example.priceservice.domain.model.Price;
import com.example.priceservice.infrastructure.persistence.entity.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {
    public Price toDomain(PriceEntity e) {
        return new Price(
                e.getBrandId(),
                e.getProductId(),
                e.getPriceList(),
                e.getStartDate(),
                e.getEndDate(),
                e.getPriority(),
                e.getPrice(),
                e.getCurr()
        );
    }
}
