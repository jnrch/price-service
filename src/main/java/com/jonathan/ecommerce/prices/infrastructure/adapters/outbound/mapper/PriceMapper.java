package com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.mapper;

import com.jonathan.ecommerce.prices.domain.model.Price;
import com.jonathan.ecommerce.prices.infrastructure.adapters.inbound.rest.response.PriceResponseDto;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence.h2.entity.H2PriceEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PriceMapper {

    Price H2PriceEntityToPrice(H2PriceEntity h2PriceEntity);

    PriceResponseDto toPriceResponseDto(Price price);
}
