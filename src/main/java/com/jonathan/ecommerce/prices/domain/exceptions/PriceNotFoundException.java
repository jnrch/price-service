package com.jonathan.ecommerce.prices.domain.exceptions;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(String message){
        super(message);
    }
}
