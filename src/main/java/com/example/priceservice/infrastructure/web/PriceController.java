package com.example.priceservice.infrastructure.web;

import com.example.priceservice.application.GetApplicablePriceUseCase;
import com.example.priceservice.infrastructure.web.dto.PriceResponseDto;
import com.example.priceservice.shared.error.PriceNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {
    private final GetApplicablePriceUseCase useCase;

    public PriceController(GetApplicablePriceUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public Mono<PriceResponseDto> getPrice(
            @RequestParam @NotNull Long brandId,
            @RequestParam @NotNull Long productId,
            @RequestParam @NotNull
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate
    ) {
        return useCase.execute(brandId, productId, applicationDate)
                .switchIfEmpty(Mono.error(new PriceNotFoundException(brandId, productId, applicationDate.toString())))
                .map(p -> new PriceResponseDto(
                        p.productId(),
                        p.brandId(),
                        p.priceList(),
                        p.startDate(),
                        p.endDate(),
                        p.price(),
                        p.currency()
                ));
    }


}
