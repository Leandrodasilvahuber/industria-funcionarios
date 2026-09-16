package br.com.industria.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Formatação de datas e números no padrão brasileiro (requisito 3.3). */
public final class FormatadorUtil {

    public static final Locale LOCALE_BR = Locale.forLanguageTag("pt-BR");

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String PADRAO_NUMERO = "#,##0.00";

    private FormatadorUtil() {
    }

    public static String formatarData(LocalDate data) {
        return data.format(FORMATO_DATA);
    }

    public static String formatarNumero(BigDecimal valor) {
        DecimalFormat formato = new DecimalFormat(PADRAO_NUMERO, DecimalFormatSymbols.getInstance(LOCALE_BR));
        return formato.format(valor);
    }

    public static String formatarMoeda(BigDecimal valor) {
        return "R$ " + formatarNumero(valor);
    }
}
