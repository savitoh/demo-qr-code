package com.savitoh.demoqrcodeapi.utils;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

public class DateUltilUnitTest {

    @Test
    public void deveFormatarData() throws ParseException {
        String dataFormatada = DateUltil.dataToPjeFormato("2019-07-15 11:06:05.76");
        Assert.assertEquals("190715110605076", dataFormatada);
    }

    @Test(expected = ParseException.class)
    public void deveLancarExcecaoQuandoFormatoNaoForValido() throws ParseException {
        String failDate = DateUltil.dataToPjeFormato("2019");
        Assert.assertEquals("190715110605076", failDate);
    }
}
