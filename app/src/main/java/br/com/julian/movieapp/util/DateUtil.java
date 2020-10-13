package br.com.julian.movieapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String ddMMyyyy = "dd/MM/yyyy";

    public static Date stringToDate(String dateString, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDate(Date date, String formatTo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatTo, Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
