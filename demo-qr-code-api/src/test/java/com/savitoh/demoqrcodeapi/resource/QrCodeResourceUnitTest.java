package com.savitoh.demoqrcodeapi.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savitoh.demoqrcodeapi.exceptions.CustomApiErroResponse;
import com.savitoh.demoqrcodeapi.exceptions.data.GenerateQrCodeException;
import com.savitoh.demoqrcodeapi.exceptions.data.URIUnknowException;
import com.savitoh.demoqrcodeapi.payload.QrCodeResquestPayload;
import com.savitoh.demoqrcodeapi.service.QrCodeService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;;


@RunWith(SpringRunner.class)
@WebMvcTest(value = QrCodeResource.class)
public class QrCodeResourceUnitTest {

    private final static String QR_CODE_STRING_BASE_64_STRING = "�PNG\n" +
            "\u001A\n" +
            "\u0000\u0000\u0000\n" +
            "IHDR\u0000\u0000\u0000�\u0000\u0000\u0000�\u0001\u0000\u0000\u0000\u0000�#�3\u0000\u0000\u0001!IDATx^�A��0\fD�*��M!�i��U�=v[U��U�Y`Qh�m,O& ���,��&7A��t��%���a�ѐ\u0011?S�\t\u0001\u007FR!!&����t�vQ6\u0012��͍�ho�;\u0015q���\n" +
            "�*��TJF6>>��JR�b�>�ך���u���7�<���<,�FPb�\u0007\n" +
            "���B|\u0005��\b�!\u0012S44���a;\u0013�Պ�h��\u0004_L�� ��y\u001A�\u001EM���m\u0002b�[�2��;FKD<��\u001Co6�!U#�:j�4$:��\b�\u0013r\u0010<! \u001F[-yH~=ũ,q��\u0001\u0007q�{�!�%����\u0016\bm�'�r�\u000F�C��i8\u0007�aq�$�G�<�s��&��\"\u007FlW,�8\u001EE\n" +
            "\u0000\u0000\u0000\u0000IEND�B`�";

    private final static String QR_CODE_URI_PATH = "/api/v1/qrcodes";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QrCodeService qrCodeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveRetornarQrCodeResponseHeader() throws Exception {

        QrCodeResquestPayload qrCodeResquestPayloadMock = new QrCodeResquestPayload("Teste");
        Mockito.when(qrCodeService.genarateQrCodeFromUri(Mockito.anyString()))
                .thenReturn(QR_CODE_STRING_BASE_64_STRING.getBytes());
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(QR_CODE_URI_PATH)
            .content(objectMapper.writeValueAsString(qrCodeResquestPayloadMock))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.IMAGE_PNG);


        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();


        Assert.assertTrue(response.containsHeader(HttpHeaders.CONTENT_DISPOSITION));
        Assert.assertNotNull(response.getOutputStream());
        Assert.assertEquals(MediaType.IMAGE_PNG_VALUE, response.getContentType());
    }

    @Test
    public void deveRetornarBadRequestQuandoRequestPayloadForInvalido() throws Exception {
        QrCodeResquestPayload qrCodeRequestPayloadInvalid = new QrCodeResquestPayload();
        
        final String messageErrorExpected = "#uriTarget nao pode ser nullo ou vazio";
        mockMvc.perform(MockMvcRequestBuilders
                .post(QR_CODE_URI_PATH)
                .content(objectMapper.writeValueAsString(qrCodeRequestPayloadInvalid))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.error").value(messageErrorExpected))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    public void deveRetornarBadRequestQuandoNaoForPossivelValidarURL() throws Exception {

        QrCodeResquestPayload qrCodeResquestPayloadMock = new QrCodeResquestPayload("Teste");
        final String messageException = "Não foi possivel validar URI";
        Mockito.when(qrCodeService.genarateQrCodeFromUri(Mockito.anyString()))
               .thenThrow(new URIUnknowException(messageException));


        mockMvc.perform(MockMvcRequestBuilders
                .post(QR_CODE_URI_PATH)
                .content(objectMapper.writeValueAsString(qrCodeResquestPayloadMock))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.error").value(messageException))
                .andExpect(jsonPath("$.timestamp").exists());        
    }


    @Test
    public void deveRetornarServerErroQuandoGerarQrCodeLancarException() throws Exception {

        QrCodeResquestPayload qrCodeResquestPayloadMock = new QrCodeResquestPayload("Teste");
        final String messageException = "Erro ao Gerar QR CODE!";
        Mockito.when(qrCodeService.genarateQrCodeFromUri(Mockito.anyString()))
               .thenThrow(new GenerateQrCodeException(messageException));
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(QR_CODE_URI_PATH)
            .content(objectMapper.writeValueAsString(qrCodeResquestPayloadMock))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        CustomApiErroResponse customApiErroResponse = 
            objectMapper.readValue(response.getContentAsString(), CustomApiErroResponse.class);
        
        
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        Assert.assertNotNull(customApiErroResponse);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), customApiErroResponse.getStatusCode());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.name(), customApiErroResponse.getStatus());
        Assert.assertEquals(messageException, customApiErroResponse.getError());
        Assert.assertTrue(customApiErroResponse.getTimestamp().isBefore(LocalDateTime.now()));
    }

}
