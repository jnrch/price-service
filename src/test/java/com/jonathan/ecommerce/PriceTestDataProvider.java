package com.jonathan.ecommerce;

import lombok.experimental.UtilityClass;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@UtilityClass
public class PriceTestDataProvider {

    public static Stream<Arguments> testCasesProvider() {
        return Stream.of(
                arguments(LocalDateTime.of(2020, 6, 14, 10, 0, 0), 35.5, 0, 1L),
                arguments(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 25.45, 1, 2L),
                arguments(LocalDateTime.of(2020, 6, 14, 21, 0, 0), 35.5, 0, 1L),
                arguments(LocalDateTime.of(2020, 6, 15, 10, 0, 0), 30.5, 1, 3L),
                arguments(LocalDateTime.of(2020, 6, 21, 16, 0, 0), 38.95, 1, 4L)
        );
    }
}
