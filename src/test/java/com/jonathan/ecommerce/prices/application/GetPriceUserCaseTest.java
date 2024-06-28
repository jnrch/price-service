package com.jonathan.ecommerce.prices.application;

import com.jonathan.ecommerce.TestConstant;
import com.jonathan.ecommerce.prices.application.services.GetPriceUserCase;
import com.jonathan.ecommerce.prices.domain.exceptions.PriceNotFoundException;
import com.jonathan.ecommerce.prices.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestPropertySource(locations = TestConstant.TEST_PROPERTY_SOURCE_LOCATION)
@SpringBootTest
@Sql(TestConstant.SQL_FILE_LOCATION)
class GetPriceUserCaseTest {

    @Autowired
    GetPriceUserCase getPriceUserCase;

    @ParameterizedTest
    @MethodSource("com.jonathan.ecommerce.PriceTestDataProvider#testCasesProvider")
    void testGetPrice_byGivenParameters(LocalDateTime applicationDate, Double expectedPrice, Integer expectedPriority, Long expectedPriceList) {
        Long brandId = 1L;
        Long productId = 35455L;

        Price actualPrice = getPriceUserCase.getPriceByParameters(brandId, productId, applicationDate);

        assertNotNull(actualPrice);
        assertEquals(1L, actualPrice.getBrandId());
        assertEquals(35455L, actualPrice.getProductId());
        assertEquals("EUR", actualPrice.getCurr());
        assertEquals(expectedPrice, actualPrice.getPrice());
        assertEquals(expectedPriority, actualPrice.getPriority());
        assertEquals(expectedPriceList, actualPrice.getPriceList());
    }

    @Test
    public void testGetPrice_throwNotFoundException() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.of(2022, 6, 20, 21,0,0);
        String expectedErrorMessage = String.format("Price not found for Brand ID: %d, Product ID: %d, and Application Date: %s", brandId, productId, applicationDate);

        try {
            getPriceUserCase.getPriceByParameters(brandId, productId, applicationDate);
        } catch (PriceNotFoundException ex) {
            assertNotNull(ex);
            assertEquals(expectedErrorMessage, ex.getMessage());
        }
    }

}