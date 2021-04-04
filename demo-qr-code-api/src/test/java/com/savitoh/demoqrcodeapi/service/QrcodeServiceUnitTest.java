package com.savitoh.demoqrcodeapi.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class QrcodeServiceUnitTest {

    @InjectMocks
    private QrCodeServiceImpl qrCodeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sobeIlegalArgumentExceptionQuandoUrlNulla() {
        qrCodeService.genarateQrCodeFromUri(null);
    }

    @Test
    public void genarateQrCodeFromValidUri() {
        final var validUrl = "https://www.journaldev.com/";

        final byte[] qrCode = qrCodeService.genarateQrCodeFromUri(validUrl);
        
        assertNotNull(qrCode);
        assertTrue(qrCode.length > 0);
    }

}
