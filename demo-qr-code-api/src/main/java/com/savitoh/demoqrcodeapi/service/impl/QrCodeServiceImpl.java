package com.savitoh.demoqrcodeapi.service.impl;

import java.io.IOException;

import com.google.zxing.WriterException;
import com.savitoh.demoqrcodeapi.exceptions.data.GenerateQrCodeException;
import com.savitoh.demoqrcodeapi.exceptions.data.URIUnknowException;
import com.savitoh.demoqrcodeapi.service.QrCodeService;
import com.savitoh.demoqrcodeapi.utils.HttpUtil;
import com.savitoh.demoqrcodeapi.utils.QrCodeUtil;

import org.springframework.stereotype.Service;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    private static final int DEFAULT_WIDTH = 200;

    private static final int DEFAULT_HEIGHT = 200;

    @Override
    public byte[] genarateQrCodeFromUri(final String uriTarget) {
        try {
            checkIfExists(uriTarget);
            return QrCodeUtil.getQRCodeImageByteArray(uriTarget, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        } catch (WriterException | IllegalArgumentException | IOException e) {
            throw new GenerateQrCodeException("Erro na geração do QrCode: " + e.getLocalizedMessage());
        }
    }

    private void checkIfExists(final String uriTarget) {
        try {
           final boolean urlExists = HttpUtil.uriExists(uriTarget);
           if(!urlExists) {
                throw  new URIUnknowException(String.format("A URL: %s não existe", uriTarget));
            }
        } catch (IOException e) {
            throw new URIUnknowException(String.format("Não foi possível chegar a URL: %s", uriTarget));
        }
    }
}
