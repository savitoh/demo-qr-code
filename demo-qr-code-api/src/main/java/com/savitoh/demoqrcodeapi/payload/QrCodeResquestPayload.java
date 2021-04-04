package com.savitoh.demoqrcodeapi.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savitoh.demoqrcodeapi.validation.UrlExists;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

public class QrCodeResquestPayload {

	@UrlExists(message = "URL não pôde ser acessada com sucesso.")
	@URL(message = "URL não possui formato válido.")
	@NotBlank(message = "URL não pode ser nulla ou vazia.")
	private final String uriTarget;

	public QrCodeResquestPayload(@JsonProperty("uriTarget") final String uriTarget) {
		this.uriTarget = uriTarget;
	}

	public String getUriTarget() {
		return uriTarget;
	}
   
}