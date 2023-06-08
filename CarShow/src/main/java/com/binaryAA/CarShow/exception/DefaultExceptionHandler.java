package com.binaryAA.CarShow.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {
    public ResponseEntity<APIError> handleException(ResourceNotFoundException e, HttpServletRequest req) {
        APIError apiError = new APIError(req.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
