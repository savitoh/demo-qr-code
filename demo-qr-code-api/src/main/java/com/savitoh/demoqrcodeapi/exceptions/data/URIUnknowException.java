package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class URIUnknowException extends CustomGlobalException {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

	public URIUnknowException(String message) {
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
