package com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence.h2;

import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence.h2.entity.H2PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface H2PriceRepositoryInternal extends JpaRepository<H2PriceEntity, Long> {

    Optional<H2PriceEntity> findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long brandId, Long productId, LocalDateTime applicationDate, LocalDateTime applicationDate2);
}
