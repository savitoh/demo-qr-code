package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;

public class CustomClientApiException extends CustomGlobalException {

    /**
	 *
	 */
	private static final long serialVersionUID = 6847611004623607334L;

    public CustomClientApiException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
    
}