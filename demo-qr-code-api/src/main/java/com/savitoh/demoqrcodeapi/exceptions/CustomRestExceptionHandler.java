package com.savitoh.demoqrcodeapi.exceptions;

import java.util.Optional;
import java.util.stream.Collectors;

import com.savitoh.demoqrcodeapi.exceptions.data.CustomGlobalException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public final class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var responseErro = new CustomApiErroResponse.Builder()
            .withStatusCode(status.value())
            .withStatus(status.name())
            .withError(ex.getBindingResult())
            .build();
        
        return new ResponseEntity<>(responseErro, status);
	}


    @ExceptionHandler(CustomGlobalException.class)
    public ResponseEntity<CustomApiErroResponse> handleCustomGlobalException(
            @NonNull CustomGlobalException customGlobalException) {
        
        Assert.notNull(customGlobalException, "CustomGlobalException não pode ser nullo");
    
        var httpStatus = Optional.ofNullable(customGlobalException.getHttpStatus())
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    
        var responseErro = new CustomApiErroResponse.Builder()
            .withStatusCode(httpStatus.value())
            .withStatus(httpStatus.name())
            .withError(customGlobalException.getMessageError())
            .build();

        return new ResponseEntity<>(responseErro, httpStatus);
    }

    private String createMessageErro(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
            .stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.joining(" - "));
    }
}
