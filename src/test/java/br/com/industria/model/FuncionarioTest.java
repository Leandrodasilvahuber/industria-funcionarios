package br.com.industria.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    private static final LocalDate NASCIMENTO = LocalDate.of(1990, 1, 1);

    @Test
    void deveHerdarDePessoa() {
        Funcionario f = new Funcionario("Maria", NASCIMENTO, new BigDecimal("1000"), "Operador");

        assertInstanceOf(Pessoa.class, f);
        assertEquals("Maria", f.getNome());
        assertEquals(NASCIMENTO, f.getDataNascimento());
        assertEquals(new BigDecimal("1000.00"), f.getSalario());
    }

    @Test
    void naoDeveAceitarSalarioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Funcionario("Maria", NASCIMENTO, new BigDecimal("-1"), "Operador"));
    }

    @Test
    void naoDeveAceitarFuncaoEmBranco() {
        assertThrows(IllegalArgumentException.class,
                () -> new Funcionario("Maria", NASCIMENTO, BigDecimal.TEN, "  "));
    }

    @Test
    void naoDeveAceitarAumentoNegativo() {
        Funcionario f = new Funcionario("Maria", NASCIMENTO, new BigDecimal("1000"), "Operador");
        assertThrows(IllegalArgumentException.class, () -> f.aplicarAumento(new BigDecimal("-5")));
    }
}
