package com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence;

import com.jonathan.ecommerce.prices.domain.model.Price;
import com.jonathan.ecommerce.prices.application.ports.outbound.PriceRepository;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.mapper.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriceRepositoryHibernate implements PriceRepository {

    private final PriceRepositoryInternalHibernate priceRepositoryInternalHibernate;
    private final PriceMapper priceMapper;

    @Override
    public Optional<Price> findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(Long brandId, Long productId, LocalDateTime applicationDate, LocalDateTime applicationDate2) {
        return priceRepositoryInternalHibernate.
                findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brandId, productId, applicationDate, applicationDate2)
                .map(priceMapper::toPrice);
    }
}
