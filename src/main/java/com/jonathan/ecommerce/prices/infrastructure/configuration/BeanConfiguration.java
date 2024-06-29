package com.jonathan.ecommerce.prices.infrastructure.configuration;

import com.jonathan.ecommerce.prices.application.ports.outbound.PriceRepository;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.mapper.PriceMapper;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence.h2.H2PriceRepository;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence.h2.H2PriceRepositoryInternal;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Beans configuration
 */
@Configuration
public class BeanConfiguration {

    @Bean
    @Primary
    public PriceRepository createPriceRepository(H2PriceRepositoryInternal h2PriceRepositoryInternal, PriceMapper priceMapper) {
        // You can change this for MongoDB, Postgres, MySql repository implementation
        return new H2PriceRepository(h2PriceRepositoryInternal, priceMapper);
    }

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Price-service")
                        .version("1.0.0")
                        .description("Endpoints documentation")
                        .contact(new Contact()
                                .name("Jonathan")
                                .email("jonathan.rojashv@gmail.com")
                                .url("https://www.linkedin.com/in/jnrch/")));
    }
}
