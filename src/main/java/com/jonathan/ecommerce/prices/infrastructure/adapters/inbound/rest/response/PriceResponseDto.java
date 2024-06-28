package com.jonathan.ecommerce.prices.infrastructure.adapters.inbound.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponseDto {

    private Long id;

    @JsonProperty("brand_id")
    private Long brandId;

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("price_list")
    private Long priceList;

    @JsonProperty("product_id")
    private Long productId;

    private Integer priority;
    private Double price;
    private String curr;
}
