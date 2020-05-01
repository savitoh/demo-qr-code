package com.savitoh.demoqrcodeapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private DateUtil() {
    }

    public static String dataToPjeFormato(final String data) throws ParseException {

        final String defaultParttern = "yyyy-MM-dd HH:mm:ss.SSS";
        final String targetParttern = "yyMMddHHmmssSSS";

        Date date = new SimpleDateFormat(defaultParttern).parse(data);
        return new SimpleDateFormat(targetParttern).format(date);
    }
}
