package br.com.automacao.utils;

import java.math.BigDecimal;

public class ConvertUtil {
    public static final String NULL_VALUE = "null";
    public static final String HOJE = "HOJE";

    public static final BigDecimal toDecimal(String value) {
        return NULL_VALUE.equalsIgnoreCase(value) ? null :
                new BigDecimal(value.replaceAll("\\.", "").replaceAll(",", ".")).setScale(2);
    }

    public static final String toText(String value) {
        return NULL_VALUE.equalsIgnoreCase(value) ? null : value.replaceAll("\"", "");
    }

    public static final String toFormattedDate(String value) {
        return NULL_VALUE.equalsIgnoreCase(value) ? null : HOJE.equalsIgnoreCase(value) ? DateUtils.getFormattedDateNow() : value;
    }

    public static final Integer toInteger(String value) {
        return NULL_VALUE.equalsIgnoreCase(value) ? null : Integer.parseInt(value);
    }

    public static final Long toLong(String value) {
        return NULL_VALUE.equalsIgnoreCase(value) ? null : Long.parseLong(value);
    }
}
