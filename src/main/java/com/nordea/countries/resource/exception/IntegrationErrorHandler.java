package com.nordea.countries.resource.exception;

import com.nordea.countries.contract.error.ErrorResponse;
import com.nordea.countries.common.exception.CountryIntegrationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ControllerAdvice
public class IntegrationErrorHandler {

    @ExceptionHandler(CountryIntegrationException.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    @ResponseBody
    public ErrorResponse handleIntegrationError(Throwable throwable) {
        return ErrorResponse.builder()
                .status(SERVICE_UNAVAILABLE.value())
                .message(throwable.getMessage())
                .build();
    }
}
