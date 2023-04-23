package com.udacity.jdnd.course3.critter.exception;

public class PetNotFoundException extends ResourceNotFoundException {
    public PetNotFoundException() {
        super(String.format("Pet not found"));
    }

    public PetNotFoundException(String message) {
        super(String.format("Pet: %s", message));
    }
}
