package com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.mapper;

import com.jonathan.ecommerce.prices.domain.model.Price;
import com.jonathan.ecommerce.prices.infrastructure.adapters.inbound.rest.response.PriceResponseDto;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PriceMapper {

    Price toPrice(PriceEntity priceEntity);

    PriceResponseDto toPriceResponseDto(Price price);
}
