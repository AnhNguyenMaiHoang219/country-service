package com.nordea.countries.resource.exception;

import com.nordea.countries.common.exception.CountryNotFoundException;
import com.nordea.countries.contract.error.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(Throwable throwable) {
        return ErrorResponse.builder()
                .status(NOT_FOUND.value())
                .message(throwable.getMessage())
                .build();
    }
}
