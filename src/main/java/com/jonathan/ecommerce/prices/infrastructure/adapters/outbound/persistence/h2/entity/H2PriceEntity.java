package com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.persistence.h2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "prices")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class H2PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "price_list")
    private Long priceList;

    @Column(name = "product_id")
    private Long productId;

    private Integer priority;
    private Double price;
    private String curr;
}
