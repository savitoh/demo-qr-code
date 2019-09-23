package com.savitoh.demoqrcodeapi.service;

import com.savitoh.demoqrcodeapi.exceptions.data.GenarateQrCodeException;
import com.savitoh.demoqrcodeapi.exceptions.data.UrlException;

public interface QrCodeService {

    byte[] genarateQrCodeFromUrl(final String url) throws GenarateQrCodeException, UrlException;

    byte[] genarateQrCodeFromUrl(final String url, int width, int height);
}
