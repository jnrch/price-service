package com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence.h2;

import com.jonathan.ecommerce.prices.domain.model.Price;
import com.jonathan.ecommerce.prices.application.ports.outbound.PriceRepository;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.mapper.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class H2PriceRepository implements PriceRepository {

    private final H2PriceRepositoryInternal h2PriceRepositoryInternal;
    private final PriceMapper priceMapper;

    @Override
    public Optional<Price> findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(Long brandId, Long productId, LocalDateTime applicationDate, LocalDateTime applicationDate2) {
        return h2PriceRepositoryInternal
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brandId, productId, applicationDate, applicationDate2)
                .map(priceMapper::H2PriceEntityToPrice);
    }
}
