package com.savitoh.demoqrcode.service;

import com.savitoh.demoqrcodeapi.exceptions.data.UrlException;
import com.savitoh.demoqrcodeapi.service.impl.QrCodeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Objects;


public class QrcodeServiceUnitTest {

    @InjectMocks
    private QrCodeServiceImpl qrCodeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void genarateQrCodeFromValidUrl() {
        final var validUrl = "https://www.journaldev.com/";
        final byte[] qrCode = qrCodeService.genarateQrCodeFromUrl(validUrl);
        Assert.assertTrue(Objects.nonNull(qrCode));
        Assert.assertTrue(qrCode.length > 0);
    }

    @Test(expected = UrlException.class)
    public void deveLancarExcecaoQuandoUrlForVazia() {
        final var failUrl = "";
        final byte[] qrCode = qrCodeService.genarateQrCodeFromUrl(failUrl);
    }

    @Test(expected = UrlException.class)
    public void deveLancarExcecaoQuandoUrlNaoExiste() {
        final var failUrl = "http://googu.com.br/";
        final byte[] qrCode = qrCodeService.genarateQrCodeFromUrl(failUrl);
    }
}
