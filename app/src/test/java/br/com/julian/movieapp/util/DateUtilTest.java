package br.com.julian.movieapp.util;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void dateFormatConversionTest() {
        String dateString = "1999-12-31";

        Date date = DateUtil.stringToDate(dateString, DateUtil.yyyyMMdd);
        String finalDate = DateUtil.formatDate(date, DateUtil.ddMMyyyy);

        assertEquals(finalDate, "31/12/1999");
    }
}