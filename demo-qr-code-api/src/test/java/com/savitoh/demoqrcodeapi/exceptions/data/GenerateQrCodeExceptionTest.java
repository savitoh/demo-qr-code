package com.savitoh.demoqrcodeapi.exceptions.data;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class GenerateQrCodeExceptionTest {

    @Test
    public void testConstructor() {
        final String messageError = "Falha ao Gerar QR Code!!";

        CustomGlobalException customGlobalException = new GenerateQrCodeException(messageError);

        Assert.assertEquals(messageError, customGlobalException.getMessage());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, customGlobalException.getHttpStatus());
    }
    
}