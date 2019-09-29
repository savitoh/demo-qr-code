package com.savitoh.demoqrcodeapi.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

public class HttpUltilUnitTest {

    @Test
    public void verivyUrlWithValidUrl() throws IOException {
        final var validUrl = "https://www.journaldev.com/";
        boolean urlIsValid = HttpUltil.urlExists(validUrl);
        Assert.assertTrue(urlIsValid);
    }

    @Test(expected = MalformedURLException.class)
    public void verivyUrlWithMalFormedUrl() throws IOException {
        final var urlMalFormed = "//www.journaldev.com/;";
        boolean urlIsValid = HttpUltil.urlExists(urlMalFormed);
        Assert.assertFalse(urlIsValid);
    }

    @Test(expected = UnknownHostException.class)
    public void verivyUrlWithNonexistentUrl() throws IOException {
        final var url = "http://googu.com.br/";
        boolean urlIsValid = HttpUltil.urlExists(url);
        Assert.assertFalse(urlIsValid);
    }
}
