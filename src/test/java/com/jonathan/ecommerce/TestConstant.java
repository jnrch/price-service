package com.jonathan.ecommerce;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestConstant {

    public static final String TEST_PROPERTY_SOURCE_LOCATION = "classpath:application-test.properties";
    public static final String SQL_FILE_LOCATION = "classpath:/data-test.sql";
    public static final String TEST_CASES_PROVIDER = "com.jonathan.service.prices.PriceTestDataProvider#testCasesProvider";
}