package com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence;

import com.jonathan.ecommerce.TestConstant;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource(locations = TestConstant.TEST_PROPERTY_SOURCE_LOCATION)
@SpringBootTest
@Sql(TestConstant.SQL_FILE_LOCATION)
class PriceRepositoryInternalHibernateTest {

    @Autowired
    private PriceRepositoryInternalHibernate priceRepositoryInternalHibernate;

    @ParameterizedTest
    @MethodSource("com.jonathan.ecommerce.PriceTestDataProvider#testCasesProvider")
    void testGetPrice_byGivenParameters(LocalDateTime applicationDate, Double expectedPrice, Integer expectedPriority, Long expectedPriceList) {
        Long brandId = 1L;
        Long productId = 35455L;

        Optional<PriceEntity> actualPrice = priceRepositoryInternalHibernate.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId,
                productId,
                applicationDate,
                applicationDate);

        assertTrue(actualPrice.isPresent());
        assertEquals(1L, actualPrice.get().getBrandId());
        assertEquals(35455L, actualPrice.get().getProductId());
        assertEquals("EUR", actualPrice.get().getCurr());
        assertEquals(expectedPrice, actualPrice.get().getPrice());
        assertEquals(expectedPriority, actualPrice.get().getPriority());
        assertEquals(expectedPriceList, actualPrice.get().getPriceList());
    }

    @Test
    void testGetPrice_andPriceIsNotPresent() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.of(2050, 6, 21, 16, 0, 0);

        Optional<PriceEntity> actualPrice = priceRepositoryInternalHibernate.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId,
                productId,
                applicationDate,
                applicationDate);

        assertFalse(actualPrice.isPresent());
    }
}