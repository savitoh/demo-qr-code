package com.savitoh.demoqrcodeapi.service;

import com.google.zxing.WriterException;
import com.savitoh.demoqrcodeapi.exceptions.data.GenerateQrCodeException;
import com.savitoh.demoqrcodeapi.utils.QrCodeUtil;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    private static final int DEFAULT_WIDTH = 200;

    private static final int DEFAULT_HEIGHT = 200;

    @Override
    public byte[] genarateQrCodeFromUri(final @NonNull String uriTarget) {
        Assert.notNull(uriTarget, "uriTarget Não pode ser nulla");
        try {
            return QrCodeUtil.getQRCodeImageByteArray(uriTarget, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        } catch (WriterException | IOException e) {
            throw new GenerateQrCodeException("Erro na geração do QrCode: " + e.getLocalizedMessage());
        }
    }
}
