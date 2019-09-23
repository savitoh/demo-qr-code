package com.savitoh.demoqrcodeapi.service.impl;

import com.google.zxing.WriterException;
import com.savitoh.demoqrcodeapi.exceptions.data.GenarateQrCodeException;
import com.savitoh.demoqrcodeapi.exceptions.data.UrlException;
import com.savitoh.demoqrcodeapi.service.QrCodeService;
import com.savitoh.demoqrcodeapi.utils.HttpUltil;
import com.savitoh.demoqrcodeapi.utils.QrCodeUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Override
    public byte[] genarateQrCodeFromUrl(final String url)
            throws GenarateQrCodeException, UrlException {

        final var defaultWidth = 200;
        final var defaultHeight = 200;

        verificaUrl(url);
        try {
           byte[] qrCode = QrCodeUtil.getQRCodeImageByteArray(url, defaultWidth, defaultHeight);
           return qrCode;
        } catch (WriterException | IllegalArgumentException | IOException e) {
            e.printStackTrace();
            throw new GenarateQrCodeException("Erro na geração do QrCode: ".concat(e.getLocalizedMessage()));
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
            e.printStackTrace();
            throw new UrlException("Problema na checagem da URL: ".concat(e.getMessage()));
        }
        if(!urlExists) {
            throw  new UrlException("Url não existe");
        }
    }
}
