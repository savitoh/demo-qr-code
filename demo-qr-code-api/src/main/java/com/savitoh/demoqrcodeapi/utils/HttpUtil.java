package com.savitoh.demoqrcodeapi.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;

public final class HttpUtil {

    private HttpUtil() {
    }

    public static boolean uriExists(final String uri) throws IOException {

        final URL urlObject = new URL(uri);
        HttpURLConnection huc = (HttpURLConnection) urlObject.openConnection();
        huc.setRequestMethod("HEAD");
        final int responseCode = huc.getResponseCode();

        return responseCode == HttpStatus.OK.value();
    }
}
