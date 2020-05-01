package com.savitoh.demoqrcodeapi.exceptions;

import java.time.LocalDateTime;

import com.savitoh.demoqrcodeapi.exceptions.data.CustomGlobalException;

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
        final var htppStatus = HttpStatus.BAD_REQUEST;
        var customGlobalException = new CustomGlobalException(messageError, htppStatus);

        ResponseEntity<CustomApiErroResponse> responseEntityErro = customRestExceptionHandler.handleCustomGlobalException(customGlobalException);
        var responseErro = responseEntityErro.getBody();

        Assert.assertNotNull(responseEntityErro);
        Assert.assertTrue(responseEntityErro.hasBody());
        Assert.assertEquals(htppStatus, responseEntityErro.getStatusCode());
        Assert.assertEquals(messageError, responseErro.getError());
        Assert.assertEquals(htppStatus.value(), responseErro.getStatusCode());
        Assert.assertEquals(htppStatus.name(), responseErro.getStatus());
        Assert.assertTrue(responseErro.getTimestamp().isBefore(LocalDateTime.now()));
    }

    @Test
    public void testHandlerCustomGlobalExceptionWhenHttpStatusIsNullInException() {
        final var messageError = "Fail Erro";
        var customGlobalException = new CustomGlobalException(messageError, null);

        ResponseEntity<CustomApiErroResponse> responseEntityErro = customRestExceptionHandler.handleCustomGlobalException(customGlobalException);
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