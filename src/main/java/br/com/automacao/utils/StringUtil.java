package br.com.automacao.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import javax.swing.text.MaskFormatter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public final class StringUtil {

    public final String toString(Object object) {
        return object == null ? null : String.valueOf(object);
    }

    public final Boolean compare(Object value1, Object value2) {
        return String.valueOf(value1).equals(String.valueOf(value2));
    }

    public static String formatarNSU(final Integer nsu) {
        if (nsu != null)
            return StringUtils.leftPad(nsu.toString(), 3, '0');
        else
            return "";
    }

    public static String formatarCodigoCooperativa(final Integer cdCoop) {
        final String codigo = StringUtils.leftPad(cdCoop.toString(), 4, '0');
        return codigo.substring(0, 3) + "-" + codigo.substring(3);
    }

    public static String formatarConta(Object conta) {
        String contaFormatada = StringUtils.leftPad(String.valueOf(conta), 7, '0');
        try {
            MaskFormatter contaFormatter = new MaskFormatter("######-#");
            contaFormatter.setValueContainsLiteralCharacters(false);
            return contaFormatter.valueToString(contaFormatada);

        } catch (Exception e) {
            System.out.println("Erro ao formatar conta: " + e.getMessage());
            Assert.fail();
        }
        return null;
    }

    public static String formatarValorMonetario(String valor) {
        if (valor != null && !valor.trim().isEmpty() && !valor.equals("")) {
            return formatarValorMonetario(new BigDecimal(valor.replace(",",".")));
        }
        return "";
    }

    public static String formatarValorMonetario(BigDecimal valor) {
        if (valor == null)
            return "";

        Locale locale = new Locale("pt", "BR");
        NumberFormat formato = NumberFormat.getCurrencyInstance(locale);

        return formato.format(valor).replaceAll("[^0123456789.,-]", "");
    }

    public static String formatarDocumento(String documento) {
        String mascara = documento.trim().length() < 14 ? "###.###.###-##" : "###.###.###/####-##";

        try {
            MaskFormatter mask = new MaskFormatter(mascara);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(documento.trim());

        } catch (Exception e) {
            System.out.println("Erro ao formatar documento: " + e.getMessage());
            Assert.fail();
        }
        return null;
    }
}