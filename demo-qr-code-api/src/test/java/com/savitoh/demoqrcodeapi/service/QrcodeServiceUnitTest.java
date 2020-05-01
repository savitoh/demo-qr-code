package com.savitoh.demoqrcodeapi.service;

import com.savitoh.demoqrcodeapi.exceptions.data.URIUnknowException;
import com.savitoh.demoqrcodeapi.service.impl.QrCodeServiceImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


public class QrcodeServiceUnitTest {

    @InjectMocks
    private QrCodeServiceImpl qrCodeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void genarateQrCodeFromValidUri() {
        final var validUrl = "https://www.journaldev.com/";

        final byte[] qrCode = qrCodeService.genarateQrCodeFromUri(validUrl);
        
        Assert.assertNotNull(qrCode);
        Assert.assertTrue(qrCode.length > 0);
    }

    @Test(expected = URIUnknowException.class)
    public void throwExceptionWhenUriIsEmpty() {

        final var failUrl = "";
        
        qrCodeService.genarateQrCodeFromUri(failUrl);
    }

    @Test(expected = URIUnknowException.class)
    public void throwExceptionWhenUriNonExistentUri() {
        
        final var failUrl = "http://googu.com.br/";
        
        qrCodeService.genarateQrCodeFromUri(failUrl);
    }

    @Test(expected = URIUnknowException.class)
    public void throwExceptionWhenResourceNotFoundInUri() {
        
        final var notFoundResourceInURI = "http://www.example.com/xyz";
        
        qrCodeService.genarateQrCodeFromUri(notFoundResourceInURI);
    }
}
