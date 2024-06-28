package com.jonathan.ecommerce.prices.application.services;

import com.jonathan.ecommerce.prices.application.ports.inbound.IGetPriceUserCase;
import com.jonathan.ecommerce.prices.domain.exceptions.PriceNotFoundException;
import com.jonathan.ecommerce.prices.domain.model.Price;
import com.jonathan.ecommerce.prices.application.ports.outbound.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class GetPriceUserCase implements IGetPriceUserCase {

    private final PriceRepository priceRepository;

    @Override
    public Price getPriceByParameters(Long brandId, Long productId, LocalDateTime applicationDate) {
        log.info("[PRICE-SERVICE] Returning price by given params brandId: {}, productId: {}, applicationDate: {}", brandId, productId, applicationDate);
        return priceRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brandId, productId, applicationDate, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("Price not found for Brand ID: %d, Product ID: %d, and Application Date: %s", brandId, productId, applicationDate)
                ));
    }
}
