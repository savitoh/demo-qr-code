package com.savitoh.demoqrcode.resource;

import com.savitoh.demoqrcodeapi.exceptions.data.GenarateQrCodeException;
import com.savitoh.demoqrcodeapi.resource.QrCodeResource;
import com.savitoh.demoqrcodeapi.service.QrCodeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

@RunWith(SpringRunner.class)
@WebMvcTest(value = QrCodeResource.class)
public class QrCodeResourceUnitTesting {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QrCodeService qrCodeService;

    private final String qrCode = "�PNG\n" +
            "\u001A\n" +
            "\u0000\u0000\u0000\n" +
            "IHDR\u0000\u0000\u0000�\u0000\u0000\u0000�\u0001\u0000\u0000\u0000\u0000�#�3\u0000\u0000\u0001!IDATx^�A��0\fD�*��M!�i��U�=v[U��U�Y`Qh�m,O& ���,��&7A��t��%���a�ѐ\u0011?S�\t\u0001\u007FR!!&����t�vQ6\u0012��͍�ho�;\u0015q���\n" +
            "�*��TJF6>>��JR�b�>�ך���u���7�<���<,�FPb�\u0007\n" +
            "���B|\u0005��\b�!\u0012S44���a;\u0013�Պ�h��\u0004_L�� ��y\u001A�\u001EM���m\u0002b�[�2��;FKD<��\u001Co6�!U#�:j�4$:��\b�\u0013r\u0010<! \u001F[-yH~=ũ,q��\u0001\u0007q�{�!�%����\u0016\bm�'�r�\u000F�C��i8\u0007�aq�$�G�<�s��&��\"\u007FlW,�8\u001EE\n" +
            "\u0000\u0000\u0000\u0000IEND�B`�";

    @Test
    public void deveRetornarQrCodeNoResponseHeader() throws Exception {
        Mockito.when(qrCodeService.genarateQrCodeFromUrl(Mockito.anyString()))
                .thenReturn(qrCode.getBytes());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/qrcode")
                                                              .param("urlTarget", "Teste")
                                                              .accept(MediaType.IMAGE_PNG);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assert.assertTrue(response.containsHeader("Content-Disposition"));
        Assert.assertTrue(Objects.nonNull(response.getOutputStream()));
        Assert.assertEquals("image/png", response.getContentType());
    }

    @Test
    public void deveRetornarServiceErroQuandoServiceLancarExcecao() throws Exception {
        Mockito.when(qrCodeService.genarateQrCodeFromUrl(Mockito.anyString()))
               .thenThrow(GenarateQrCodeException.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/qrcode")
                                                              .param("urlTarget", "Teste")
                                                              .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(500, response.getStatus());
    }

}
