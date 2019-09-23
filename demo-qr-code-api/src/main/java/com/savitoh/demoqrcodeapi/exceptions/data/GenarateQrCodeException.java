package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GenarateQrCodeException extends RuntimeException {

    public GenarateQrCodeException(String message) {
        super(message);
    }
}
