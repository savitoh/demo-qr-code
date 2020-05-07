package com.savitoh.demoqrcodeapi.resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.savitoh.demoqrcodeapi.payload.QrCodeResquestPayload;
import com.savitoh.demoqrcodeapi.service.QrCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;



@RestController
@RequestMapping("api/v1/qrcodes")
public class QrCodeResource {

    private final QrCodeService qrCodeService;

    @Autowired
    public QrCodeResource(final QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping
    public StreamingResponseBody generateQrCode(@RequestBody @Validated QrCodeResquestPayload qrCodeResquestPayload,
                                                final HttpServletResponse response) {

        final String urlTarget = qrCodeResquestPayload.getUriTarget();                                                        
        final byte[] qrCode = qrCodeService.genarateQrCodeFromUri(urlTarget);
        final InputStream targetStream = new ByteArrayInputStream(qrCode);
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=\"QrCode.png\"");
        return outputStream -> {
            int nRead;
            final byte[] data = new byte[1024];
            while ((nRead = targetStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
        };
    }
    
}
