package com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence;

import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepositoryInternalHibernate extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity> findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long brandId, Long productId, LocalDateTime applicationDate, LocalDateTime applicationDate2);
}
