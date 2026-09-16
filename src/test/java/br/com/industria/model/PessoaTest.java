package br.com.industria.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    @Test
    void deveCriarPessoaValida() {
        Pessoa p = new Pessoa("Maria", LocalDate.of(1990, 1, 1));
        assertEquals("Maria", p.getNome());
        assertEquals(LocalDate.of(1990, 1, 1), p.getDataNascimento());
    }

    @Test
    void deveCalcularIdadeNaDataDeReferencia() {
        Pessoa p = new Pessoa("Maria", LocalDate.of(2000, 10, 18));
        assertEquals(26, p.getIdade(LocalDate.of(2026, 10, 18)));
        assertEquals(25, p.getIdade(LocalDate.of(2026, 10, 17)));
    }

    @Test
    void naoDeveAceitarNomeEmBranco() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pessoa("  ", LocalDate.of(1990, 1, 1)));
    }

    @Test
    void naoDeveAceitarDataDeNascimentoFutura() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pessoa("Maria", LocalDate.now().plusDays(1)));
    }

    @Test
    void naoDeveAceitarValoresNulos() {
        assertThrows(NullPointerException.class, () -> new Pessoa(null, LocalDate.of(1990, 1, 1)));
        assertThrows(NullPointerException.class, () -> new Pessoa("Maria", null));
    }
}
