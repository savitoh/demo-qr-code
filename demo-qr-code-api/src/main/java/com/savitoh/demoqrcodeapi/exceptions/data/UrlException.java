package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UrlException extends CustomGlobalException {

    public UrlException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
