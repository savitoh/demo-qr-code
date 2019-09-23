package com.savitoh.demoqrcodeapi.resource;

import com.savitoh.demoqrcodeapi.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("api/v1/")
@Validated
public class QrCodeResource {

    final QrCodeService qrCodeService;

    @Autowired
    public QrCodeResource(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(value = "qrcode")
    public StreamingResponseBody generateQrCode(@RequestParam("urlTarget")
                                                @NotEmpty(message = "Parâmetro urlTarget não pode ser vazio")
                                                final String urlTarget,
                                                HttpServletResponse response) {

        final byte[] qrCode = qrCodeService.genarateQrCodeFromUrl(urlTarget);
        final InputStream targetStream = new ByteArrayInputStream(qrCode);
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=\"QrCode.png\"");
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = targetStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
        };
    }

}
