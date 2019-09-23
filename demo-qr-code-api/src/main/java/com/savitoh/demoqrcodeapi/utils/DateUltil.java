package com.savitoh.demoqrcodeapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUltil {

    private static final Logger logger = LoggerFactory.getLogger("DateUtil");

    private DateUltil() {
    }

    public static String dataToPjeFormato(final String data) throws ParseException {

        final String defaultParttern = "yyyy-MM-dd HH:mm:ss.SSS";
        final String targetParttern = "yyMMddHHmmssSSS";

        try {
            Date date = new SimpleDateFormat(defaultParttern).parse(data);
            return new SimpleDateFormat(targetParttern).format(date);
        } catch (ParseException e) {
            logger.error("Error Format Data {} ", e);
            throw e;
        }
    }
}
