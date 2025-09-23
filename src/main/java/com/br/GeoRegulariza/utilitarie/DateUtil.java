/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.GeoRegulariza.utilitarie;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author johnny
 */

public class DateUtil implements Serializable {

    public static final String DD_MMMM_DE_YYYY_HH_MM_SS = "dd 'de' MMMM 'de' yyyy HH:mm:ss";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    public static LocalDateTime dateToLocalDatetime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
    
    public static Date localDateToDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static String format(String pattern, Date data, Locale locale) {
        if (locale == null) {
            locale = Locale.of("pt", "BR");
        }
        return new SimpleDateFormat(pattern, locale).format(data);
    }
    
    public static String format(String pattern, Date data) {
        return format(pattern, data, null);
    }

    public static long differenceMinutes(Date date1, Date date2) {
        long differenceInMinutes = Duration.between(dateToLocalDatetime(date1), dateToLocalDatetime(date2)).toMinutes();
        return Math.abs(differenceInMinutes);
    }
    
    public static String formatarData(Date date) {
        LocalDateTime localDate = DateUtil.dateToLocalDatetime(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDate.format(formatter);
    }
    
    public static String formatarData2(Date date) {
        LocalDateTime localDate = DateUtil.dateToLocalDatetime(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }
    
}
