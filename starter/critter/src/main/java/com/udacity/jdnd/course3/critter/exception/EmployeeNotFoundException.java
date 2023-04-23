package com.udacity.jdnd.course3.critter.exception;

public class EmployeeNotFoundException extends ResourceNotFoundException {
    public EmployeeNotFoundException() {
        super(String.format("Employee not found"));
    }

    public EmployeeNotFoundException(String message) {
        super(String.format("Employee: %s", message));
    }
}
