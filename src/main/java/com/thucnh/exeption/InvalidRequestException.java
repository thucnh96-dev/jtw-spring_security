package com.thucnh.exeption;

import org.springframework.validation.BindingResult;

public class InvalidRequestException extends RuntimeException {
    private BindingResult errors;

    public InvalidRequestException(BindingResult errors) {
        this.errors = errors;
    }

    public BindingResult getErrors() {
        return errors;
    }
}
