package br.com.automacao.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final String  ZONE_ID = "America/Sao_Paulo";

    public static final String PATTERN = "dd/MM/yyyy";

    public static final LocalDateTime toLocalDateTime(LocalDate localDate) {
        LocalTime time = LocalTime.MIN;

        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    public static final Date toSqlDate(LocalDateTime localDateTime) {
        return java.sql.Date.valueOf(localDateTime.toLocalDate());
    }

    public static final Date toUtilDate(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            ZonedDateTime zdt = localDateTime.atZone(ZoneId.of(ZONE_ID));
            return Date.from(zdt.toInstant());
        }

        return null;
    }

    public static final String formatDate(LocalDate data) {
        if (data != null)
            return DateTimeFormatter.ofPattern(PATTERN).format(data);
        return "";
    }

    public static final LocalDate parse(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(PATTERN));
    }

    public static final String getFormattedDateNow() {
        return formatDate(LocalDate.now());
    }

    public static Date aumentarDiasNaData(int dias) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, dias);

        return cal.getTime();
    }

    public static Date diminuirDiasNaData(int dias) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -dias);

        return cal.getTime();
    }

    public static String converterData(LocalDate dataLocal) {
        DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataLocal.format(formatadorBarra);
    }
}