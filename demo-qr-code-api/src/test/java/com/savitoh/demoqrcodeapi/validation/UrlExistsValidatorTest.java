package com.savitoh.demoqrcodeapi.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UrlExistsValidatorTest {

    @Mock
    private RestTemplate restTemplate;

    private UrlExistsValidator urlExistsValidator;

    @Before
    public void setUp() {
        urlExistsValidator = new UrlExistsValidator(restTemplate);
    }

    @Test
    public void deveRetornarFalseQuandoValidarURLNula() {
            final boolean urlExists = urlExistsValidator.isValid(null, null);

            assertFalse(urlExists);
    }

    @Test
    public void deveRetornarFalseQuandoReceberClientErro() {
        final String fakeUrl = "www.fakesite.com";
        when(restTemplate.headForHeaders(fakeUrl)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        final boolean urlExists = urlExistsValidator.isValid(fakeUrl, null);

        assertFalse(urlExists);
    }

    @Test
    public void deveRetornarFalseQuandoReceberServerErro() {
        final String fakeUrl = "www.fakesite.com";
        when(restTemplate.headForHeaders(fakeUrl))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        final boolean urlExists = urlExistsValidator.isValid(fakeUrl, null);

        assertFalse(urlExists);
    }

    @Test
    public void deveRetornarTrueQuandoReceberReponseHeadersComSucesso() {
        final String fakeUrl = "www.fakesite.com";
        when(restTemplate.headForHeaders(fakeUrl)).thenReturn(HttpHeaders.EMPTY);

        final boolean urlExists = urlExistsValidator.isValid(fakeUrl, null);

        assertTrue(urlExists);
    }

}