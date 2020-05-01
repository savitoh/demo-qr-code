package com.savitoh.demoqrcodeapi.exceptions;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import com.savitoh.demoqrcodeapi.exceptions.data.CustomGlobalException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public final class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        final var msgErro = new StringBuilder("Parâmetro: ")
                                    .append(ex.getParameterName())
                                    .append(" obrigatório na url")
                                    .toString();
        var responseErro = new CustomApiErroResponse.Builder()
                                                .withStatusCode(status.value())
                                                .withStatus(status.name())
                                                .withError(msgErro)
                                                .build();
        return new ResponseEntity<>(responseErro, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomApiErroResponse> handleConstraintViolationException(RuntimeException ex) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var responseErro = new CustomApiErroResponse.Builder()
                                                .withStatusCode(httpStatus.value())
                                                .withStatus(httpStatus.name())
                                                .withError(ex.getLocalizedMessage())
                                                .build();
        return new ResponseEntity<>(responseErro, httpStatus);
    }


    @ExceptionHandler(CustomGlobalException.class)
    public ResponseEntity<CustomApiErroResponse> handleCustomGlobalException(@NonNull CustomGlobalException ex) {
        Assert.notNull(ex, "CustomGlobalException não pode ser nullo");
        var httpStatus = Optional.ofNullable(ex.getHttpStatus())
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        var responseErro = new CustomApiErroResponse.Builder()
                                                .withStatusCode(httpStatus.value())
                                                .withStatus(httpStatus.name())
                                                .withError(ex.getMessage())
                                                .build();
        return new ResponseEntity<>(responseErro, httpStatus);
    }

}
