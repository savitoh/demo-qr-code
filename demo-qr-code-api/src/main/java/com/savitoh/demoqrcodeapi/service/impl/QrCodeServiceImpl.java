package com.savitoh.demoqrcodeapi.service.impl;

import com.google.zxing.WriterException;
import com.savitoh.demoqrcodeapi.exceptions.data.GenarateQrCodeException;
import com.savitoh.demoqrcodeapi.exceptions.data.UrlException;
import com.savitoh.demoqrcodeapi.service.QrCodeService;
import com.savitoh.demoqrcodeapi.utils.HttpUltil;
import com.savitoh.demoqrcodeapi.utils.QrCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    private static final Logger logger = LoggerFactory.getLogger("QrCodeServiceImpl");

    private static final int DEFAULT_WIDTH = 200;

    private static final int DEFAULT_HEIGHT = 200;

    @Override
    public byte[] genarateQrCodeFromUrl(final String url)
            throws GenarateQrCodeException, UrlException {

        verificaUrl(url);
        try {
           return QrCodeUtil.getQRCodeImageByteArray(url, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        } catch (WriterException | IllegalArgumentException | IOException e) {
            logger.error("Error gerenate Qr CODE {} ", e);
            throw new GenarateQrCodeException("Erro na geração do QrCode: " + e.getLocalizedMessage());
        }
    }

    @Override
    public byte[] genarateQrCodeFromUrl(String url, int width, int height) {
        return new byte[0];
    }

    private void verificaUrl(final String url) throws UrlException {
        final boolean urlExists;
        try {
           urlExists = HttpUltil.urlExists(url);
        } catch (IOException e) {
            logger.error("Error Checagem URL: {}", e);
            throw new UrlException("Problema na checagem da URL: " + e.getMessage());
        }
        if(!urlExists) {
            throw  new UrlException("Url não existe");
        }
    }
}
