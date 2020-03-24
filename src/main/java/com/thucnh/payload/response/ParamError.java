package com.thucnh.payload.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class ParamError {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ParamError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ParamError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
