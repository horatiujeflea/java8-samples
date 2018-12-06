package com.horatiuj.sample.gps.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestUtil {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static Long stringToDate(String date) throws ParseException {
        return df.parse(date).getTime();
    }
}
