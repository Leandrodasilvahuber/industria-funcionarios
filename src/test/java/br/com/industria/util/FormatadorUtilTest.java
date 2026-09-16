package br.com.industria.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatadorUtilTest {

    @Test
    void deveFormatarData() {
        assertEquals("02/05/1961", FormatadorUtil.formatarData(LocalDate.of(1961, 5, 2)));
    }

    @Test
    void deveFormatarNumero() {
        assertEquals("19.119,88", FormatadorUtil.formatarNumero(new BigDecimal("19119.88")));
        assertEquals("1.234.567,89", FormatadorUtil.formatarNumero(new BigDecimal("1234567.89")));
    }

    @Test
    void deveExibirDuasCasas() {
        assertEquals("999,00", FormatadorUtil.formatarNumero(new BigDecimal("999")));
        assertEquals("0,50", FormatadorUtil.formatarNumero(new BigDecimal("0.5")));
    }

    @Test
    void deveFormatarMoeda() {
        assertEquals("R$ 2.210,38", FormatadorUtil.formatarMoeda(new BigDecimal("2210.38")));
    }
}
