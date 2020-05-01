package com.savitoh.demoqrcodeapi.exceptions.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UrlException extends CustomGlobalClientApiException {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public UrlException(String message) {
        super(message);
    }
}
