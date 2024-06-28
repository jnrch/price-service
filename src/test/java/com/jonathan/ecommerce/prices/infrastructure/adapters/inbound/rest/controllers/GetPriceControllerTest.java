package com.jonathan.ecommerce.prices.infrastructure.adapters.inbound.rest.controllers;

import com.jonathan.ecommerce.TestConstant;
import com.jonathan.ecommerce.prices.infrastructure.adapters.inbound.rest.response.PriceResponseDto;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.exception.ErrorResponse;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestPropertySource(locations = TestConstant.TEST_PROPERTY_SOURCE_LOCATION)
@SpringBootTest
@Sql(TestConstant.SQL_FILE_LOCATION)
@AutoConfigureMockMvc
class GetPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @ParameterizedTest
    @MethodSource("com.jonathan.ecommerce.PriceTestDataProvider#testCasesProvider")
    public void testGetPrice_byGivenParameters(LocalDateTime applicationDate, Double expectedPrice, Integer expectedPriority, Long expectedPriceList) {
        Long brandId = 1L;
        Long productId = 35455L;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String formattedDate = applicationDate.format(formatter);

        PriceResponseDto price =
                RestAssuredMockMvc.given()
                        .queryParam("brand_id", brandId)
                        .queryParam("product_id", productId)
                        .queryParam("application_date", formattedDate)
                        .when()
                        .get("/prices")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(PriceResponseDto.class);

        assertNotNull(price);
        assertEquals(1L, price.getBrandId());
        assertEquals(35455L, price.getProductId());
        assertEquals("EUR", price.getCurr());
        assertEquals(expectedPrice, price.getPrice());
        assertEquals(expectedPriority, price.getPriority());
        assertEquals(expectedPriceList, price.getPriceList());
    }

    @Test
    public void testGetPrice_throwNotFoundException() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.of(2022, 6, 21, 16, 0, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String formattedDate = applicationDate.format(formatter);
        String expectedErrorMessage = "Price not found for given parameters.";
        List<String> expectedErrorDetails = List.of(String.format("Price not found for Brand ID: %d, Product ID: %d, and Application Date: %s", brandId, productId, applicationDate));

        ErrorResponse errorResponse =
                RestAssuredMockMvc.given()
                        .queryParam("brand_id", brandId)
                        .queryParam("product_id", productId)
                        .queryParam("application_date", formattedDate)
                        .when()
                        .get("/prices")
                        .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .extract()
                        .as(ErrorResponse.class);

        assertNotNull(errorResponse);
        assertEquals(expectedErrorMessage, errorResponse.getMessage());
        assertEquals(expectedErrorDetails, errorResponse.getDetails());
    }

    @Test
    public void testGetPrice_throwBadRequest() {
        String brandId = "brandId";
        String productId = "productId";
        String applicationDate = "applicationDate";
        String expectedErrorMessage = "Please ensure the correct data type is used for this field.";

        ErrorResponse errorResponse =
                RestAssuredMockMvc.given()
                        .queryParam("brand_id", brandId)
                        .queryParam("product_id", productId)
                        .queryParam("application_date", applicationDate)
                        .when()
                        .get("/prices")
                        .then()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .extract()
                        .as(ErrorResponse.class);

        assertNotNull(errorResponse);
        assertEquals(expectedErrorMessage, errorResponse.getMessage());
    }
}