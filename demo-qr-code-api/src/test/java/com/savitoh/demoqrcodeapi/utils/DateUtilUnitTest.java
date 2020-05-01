package com.savitoh.demoqrcodeapi.utils;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilUnitTest {

    @Test
    public void deveFormatarData() throws ParseException {
        String dataFormatada = DateUtil.dataToPjeFormato("2019-07-15 11:06:05.76");

        Assert.assertEquals("190715110605076", dataFormatada);
    }

    @Test(expected = ParseException.class)
    public void deveLancarExcecaoQuandoFormatoNaoForValido() throws ParseException {
        DateUtil.dataToPjeFormato("2019");
    }
}
