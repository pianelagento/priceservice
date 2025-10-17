package com.example.priceservice.infrastructure.config;

import com.example.priceservice.application.GetApplicablePriceUseCase;
import com.example.priceservice.domain.repository.PricePort;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class R2dbcConfig {
    @Bean
    GetApplicablePriceUseCase getApplicablePriceUseCase(PricePort pricePort) {
        return new GetApplicablePriceUseCase(pricePort);
    }

    // Inicializar schema.sql y data.sql para R2DBC (H2)
    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        var initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));

        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
