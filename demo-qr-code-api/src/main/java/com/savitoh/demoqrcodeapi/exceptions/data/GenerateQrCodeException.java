package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GenerateQrCodeException extends CustomServerException {

    /**
	 *
	 */
	private static final long serialVersionUID = -848421787660787514L;
	
	public GenerateQrCodeException(String message) {
        super(message);
    }
}
