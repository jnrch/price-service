package com.jonathan.ecommerce.prices.infrastructure.configuration;

import com.jonathan.ecommerce.prices.application.ports.outbound.PriceRepository;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence.PriceRepositoryHibernate;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence.PriceRepositoryInternalHibernate;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.mapper.PriceMapper;
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
    public PriceRepository createPriceRepository(PriceRepositoryInternalHibernate productRepositoryInternalHibernate, PriceMapper priceMapper) {
        // You can change this for the Hibernate implementation
        return new PriceRepositoryHibernate(productRepositoryInternalHibernate, priceMapper);
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
