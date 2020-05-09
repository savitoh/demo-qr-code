package com.savitoh.demoqrcodeapi.exceptions;

import java.time.LocalDateTime;

import com.savitoh.demoqrcodeapi.exceptions.data.GenerateQrCodeException;
import com.savitoh.demoqrcodeapi.helpers.exception.FailExceptionWithHttpStatusNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomRestExceptionHandlerTest {

    
    @InjectMocks
    private CustomRestExceptionHandler customRestExceptionHandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandlerCustomGlobalException() {

        final var messageError = "Fail Erro";
        var generateQrCodeException = new GenerateQrCodeException(messageError);

        ResponseEntity<CustomApiErroResponse> responseEntityErro = 
            customRestExceptionHandler.handleCustomGlobalException(generateQrCodeException);
       
            
        var responseErro = responseEntityErro.getBody();
        var httpStatusExpected = generateQrCodeException.getHttpStatus();
        Assert.assertNotNull(responseEntityErro);
        Assert.assertTrue(responseEntityErro.hasBody());
        Assert.assertEquals(httpStatusExpected, responseEntityErro.getStatusCode());
        Assert.assertEquals(messageError, responseErro.getError());
        Assert.assertEquals(httpStatusExpected.value(), responseErro.getStatusCode());
        Assert.assertEquals(httpStatusExpected.name(), responseErro.getStatus());
        Assert.assertTrue(responseErro.getTimestamp().isBefore(LocalDateTime.now()));
    }

    @Test
    public void testHandlerCustomGlobalExceptionWhenHttpStatusIsNullInException() {
        final var messageError = "Fail Erro";
        var customGlobalException = new FailExceptionWithHttpStatusNull(messageError);

        ResponseEntity<CustomApiErroResponse> responseEntityErro = 
            customRestExceptionHandler.handleCustomGlobalException(customGlobalException);
        var responseErro = responseEntityErro.getBody();

        Assert.assertNotNull(responseEntityErro);
        Assert.assertTrue(responseEntityErro.hasBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntityErro.getStatusCode());
        Assert.assertEquals(messageError, responseErro.getError());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseErro.getStatusCode());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.name(), responseErro.getStatus());
        Assert.assertTrue(responseErro.getTimestamp().isBefore(LocalDateTime.now()));
    }

 
    @Test(expected = IllegalArgumentException.class)
    public void testHandlerCustomGlobalExceptionThrowIlegalArgumentExceptionWhenExceptionIsNull() {
        customRestExceptionHandler.handleCustomGlobalException(null);
    }
    
}