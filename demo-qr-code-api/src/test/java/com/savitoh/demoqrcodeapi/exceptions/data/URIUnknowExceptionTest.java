package com.savitoh.demoqrcodeapi.exceptions.data;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class URIUnknowExceptionTest {

    @Test
    public void testConstructor() {
        final String messageError = "Falha ao Gerar QR Code!!";

        CustomGlobalException customGlobalException = new URIUnknowException(messageError);

        Assert.assertEquals(messageError, customGlobalException.getMessage());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, customGlobalException.getHttpStatus());
    }
    
}