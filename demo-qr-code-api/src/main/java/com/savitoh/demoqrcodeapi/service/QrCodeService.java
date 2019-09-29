package com.savitoh.demoqrcodeapi.service;

public interface QrCodeService {

    byte[] genarateQrCodeFromUrl(final String url);

    byte[] genarateQrCodeFromUrl(final String url, int width, int height);
}
