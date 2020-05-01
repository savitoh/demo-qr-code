package com.savitoh.demoqrcodeapi.exceptions;

import com.savitoh.demoqrcodeapi.exceptions.data.CustomGlobalServerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public final class CustomGlobalRestExceptionHandler extends ResponseEntityExceptionHandler {


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
                                                .withCodeStatus(status.value())
                                                .withStatus(status.name())
                                                .withError(msgErro)
                                                .build();
        return new ResponseEntity<>(responseErro, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomApiErroResponse> handleConstraintViolationException(RuntimeException ex) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var responseErro = new CustomApiErroResponse.Builder()
                                                .withCodeStatus(httpStatus.value())
                                                .withStatus(httpStatus.name())
                                                .withError(ex.getLocalizedMessage())
                                                .build();
        return new ResponseEntity<>(responseErro, httpStatus);
    }


    @ExceptionHandler(CustomGlobalServerException.class)
    public ResponseEntity<CustomApiErroResponse> handleGenarateQrCodeErro(CustomGlobalServerException ex) {
        var httpStatus = ex.getHttpStatus();
        var responseErro = new CustomApiErroResponse.Builder()
                                                .withCodeStatus(httpStatus.value())
                                                .withStatus(httpStatus.name())
                                                .withError(ex.getMessage())
                                                .build();
        return new ResponseEntity<>(responseErro, httpStatus);
    }

}
