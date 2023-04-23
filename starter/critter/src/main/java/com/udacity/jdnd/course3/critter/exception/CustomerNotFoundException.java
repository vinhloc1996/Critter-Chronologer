package com.udacity.jdnd.course3.critter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomerNotFoundException extends ResourceNotFoundException {

    public CustomerNotFoundException() {
        super(String.format("Customer not found"));
    }

    public CustomerNotFoundException(String message) {
        super(String.format("Customer: %s", message));
    }
}
