package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GenerateQrCodeException extends CustomGlobalException {

    /**
	 *
	 */
	private static final long serialVersionUID = -848421787660787514L;

	private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
	
	public GenerateQrCodeException(String message) {
        super(message);
    }

	@Override
	public String getMessageError() {
		return super.getMessage();
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HTTP_STATUS;
	}
}
