package com.jonathan.ecommerce.prices.application.ports.outbound;

import com.jonathan.ecommerce.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long brandId, Long productId, LocalDateTime applicationDate, LocalDateTime applicationDate2);
}
