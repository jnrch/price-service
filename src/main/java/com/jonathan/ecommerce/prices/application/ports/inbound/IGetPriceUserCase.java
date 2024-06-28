package com.jonathan.ecommerce.prices.application.ports.inbound;

import com.jonathan.ecommerce.prices.domain.model.Price;

import java.time.LocalDateTime;

public interface IGetPriceUserCase {

    Price getPriceByParameters(Long brandId, Long productId, LocalDateTime applicationDate);
}
