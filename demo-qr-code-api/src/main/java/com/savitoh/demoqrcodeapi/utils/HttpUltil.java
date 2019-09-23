package com.savitoh.demoqrcodeapi.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class HttpUltil {


    public static boolean urlExists(final String url) throws IOException {

        final URL urlObject = new URL(url);
        HttpURLConnection huc = (HttpURLConnection) urlObject.openConnection();
        huc.setRequestMethod("HEAD");
        final var responseCode = huc.getResponseCode();

        return responseCode == 200 ? true : false;
    }
}
