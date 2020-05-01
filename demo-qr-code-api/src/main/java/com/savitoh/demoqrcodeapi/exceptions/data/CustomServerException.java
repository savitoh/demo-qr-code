package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;

public abstract class CustomServerException extends CustomGlobalException {

    /**
	 *
	 */
    private static final long serialVersionUID = 8133098212662022841L;
    

    public CustomServerException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
