package com.savitoh.demoqrcodeapi.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HelloResource.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class HelloResourceUnitTest {

    private final static String CONTEXT_ROAD = "/";

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void deveRetonarStatusOkQuandoChamarContexRoot() throws Exception {
        final String responseBodyExpected = "Hello QR-Code API. Cors Origem Allowed: https://fail.com";

        mockMvc.perform(MockMvcRequestBuilders
            .get(CONTEXT_ROAD)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").value(responseBodyExpected));
    }
    
}