package com.example.priceservice.domain;

import com.example.priceservice.application.GetApplicablePriceUseCase;
import com.example.priceservice.domain.model.Price;
import com.example.priceservice.domain.repository.PricePort;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class GetApplicablePriceUseCaseTest {

    @Test
    void returns_price_from_port() {
        var port = mock(PricePort.class);
        var useCase = new GetApplicablePriceUseCase(port);

        var now = LocalDateTime.now();
        var price = new Price(1, 35455, 1, now.minusHours(1), now.plusHours(1), 0, new BigDecimal("35.50"), "EUR");
        when(port.findApplicablePrice(1, 35455, now)).thenReturn(Mono.just(price));

        StepVerifier.create(useCase.execute(1, 35455, now))
                .expectNext(price)
                .verifyComplete();

        verify(port).findApplicablePrice(1, 35455, now);
    }
}
