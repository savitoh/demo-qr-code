package com.savitoh.demoqrcodeapi.service;

public interface QrCodeService {

    byte[] genarateQrCodeFromUri(final String url);
    
}
