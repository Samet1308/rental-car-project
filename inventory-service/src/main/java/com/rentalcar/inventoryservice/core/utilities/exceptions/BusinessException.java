package com.rentalcar.inventoryservice.core.utilities.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException (String message){
        super(message);
    }
}
