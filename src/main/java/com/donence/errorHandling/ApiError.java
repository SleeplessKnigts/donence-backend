package com.donence.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private Date timestamp;

    public ApiError(final HttpStatus status, final String message, final String error, final Date date) {
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
        this.timestamp = date;
    }
}
