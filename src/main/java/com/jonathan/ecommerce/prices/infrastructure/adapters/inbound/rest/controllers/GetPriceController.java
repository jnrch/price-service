package com.jonathan.ecommerce.prices.infrastructure.adapters.inbound.rest.controllers;

import com.jonathan.ecommerce.prices.application.ports.inbound.IGetPriceUserCase;
import com.jonathan.ecommerce.prices.infrastructure.adapters.inbound.rest.response.PriceResponseDto;
import com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.mapper.PriceMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prices")
public class GetPriceController {

    private final PriceMapper priceMapper;
    private final IGetPriceUserCase getPriceUseCase;

    @GetMapping
    @Operation(summary = "Returns a price given parameters product_id, brand_id & application_date")
    public ResponseEntity<PriceResponseDto> getPrice(@Parameter(example = "1") @RequestParam("brand_id") Long brandId,
                                                     @Parameter(example = "35455") @RequestParam("product_id") Long productId,
                                                     @Parameter(example = "2020-06-15T10:00:00Z") @RequestParam("application_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") LocalDateTime applicationDate){
        return ResponseEntity.ok().body(priceMapper.toPriceResponseDto(getPriceUseCase.getPriceByParameters(brandId, productId, applicationDate)));
    }
}
