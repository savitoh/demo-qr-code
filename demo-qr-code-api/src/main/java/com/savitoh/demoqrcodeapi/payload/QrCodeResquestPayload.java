package com.savitoh.demoqrcodeapi.payload;

import javax.validation.constraints.NotBlank;

public class QrCodeResquestPayload {

	@NotBlank(message = "#uriTarget nao pode ser nullo ou vazio")
	private String uriTarget;
	
	/**
	 * @deprecated (Usado apenas para desserialização)
	 */
	@Deprecated(forRemoval = false, since = "01/05/2020")
	public QrCodeResquestPayload() {

	}

	public QrCodeResquestPayload(String uriTarget) {
		this.uriTarget = uriTarget;
	}

	public String getUriTarget() {
		return uriTarget;
	}

	public void setUriTarget(String uriTarget) {
		this.uriTarget = uriTarget;
	}
   
}