package com.savitoh.demoqrcodeapi.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class UrlExistsValidator implements ConstraintValidator<UrlExists, String> {

    private final RestTemplate restTemplate;

    public UrlExistsValidator(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.nonNull(value)) {
            try {
                restTemplate.headForHeaders(value);
                return true;
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                return false;
            }
        }
        return false;
    }
}
