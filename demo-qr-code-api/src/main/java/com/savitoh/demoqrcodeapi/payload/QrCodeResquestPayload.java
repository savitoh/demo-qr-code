package com.savitoh.demoqrcodeapi.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class QrCodeResquestPayload {

	@NotBlank(message = "#uriTarget não pode ser nullo ou vazio")
	private final String uriTarget;

	public QrCodeResquestPayload(@JsonProperty("uriTarget") final String uriTarget) {
		this.uriTarget = uriTarget;
	}

	public String getUriTarget() {
		return uriTarget;
	}
   
}