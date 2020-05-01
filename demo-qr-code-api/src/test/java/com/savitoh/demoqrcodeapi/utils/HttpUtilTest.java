package com.savitoh.demoqrcodeapi.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Test;

public class HttpUtilTest {

    @Test
    public void verivyUriWithValidUri() throws IOException {
        final var validUrl = "https://www.journaldev.com/";

        boolean uriIsValid = HttpUtil.uriExists(validUrl);

        Assert.assertTrue(uriIsValid);
    }

    @Test
    public void verivyUriWithResourceNotFoundInURI() throws IOException {
        final var validUrl = "http://www.example.com/xyz";

        boolean uriIsValid = HttpUtil.uriExists(validUrl);

        Assert.assertFalse(uriIsValid);
    }

    @Test(expected = MalformedURLException.class)
    public void verivyUriWithMalFormedUri() throws IOException {
        final var urlMalFormed = "//www.journaldev.com/;";

        HttpUtil.uriExists(urlMalFormed);
    }

    @Test(expected = UnknownHostException.class)
    public void verivyUriWithNonExistentUri() throws IOException {
        final var url = "http://googu.com.br/";

        HttpUtil.uriExists(url);
    }
}
