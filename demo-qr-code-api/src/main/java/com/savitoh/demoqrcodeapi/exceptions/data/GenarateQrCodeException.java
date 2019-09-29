package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GenarateQrCodeException extends CustomGlobalException {

    public GenarateQrCodeException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
