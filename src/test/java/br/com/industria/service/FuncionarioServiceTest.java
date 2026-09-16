package br.com.industria.service;

import br.com.industria.model.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioServiceTest {

    private FuncionarioService service;
    private List<Funcionario> funcionarios;

    @BeforeEach
    void setUp() {
        service = new FuncionarioService();
        funcionarios = new ArrayList<>(List.of(
                new Funcionario("Maria",   LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"),  "Operador"),
                new Funcionario("João",    LocalDate.of(1990, 5, 12),  new BigDecimal("2284.38"),  "Operador"),
                new Funcionario("Caio",    LocalDate.of(1961, 5, 2),   new BigDecimal("9836.14"),  "Coordenador"),
                new Funcionario("Miguel",  LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Heitor",  LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"),  "Operador"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24),  new BigDecimal("1606.85"),  "Eletricista"),
                new Funcionario("Helena",  LocalDate.of(1996, 12, 2),  new BigDecimal("2799.93"),  "Gerente")
        ));
    }

    @Test
    void deveRemoverJoao() {
        boolean removido = service.removerPorNome(funcionarios, "João");

        assertTrue(removido);
        assertEquals(6, funcionarios.size());
        assertTrue(funcionarios.stream().noneMatch(f -> f.getNome().equals("João")));
    }

    @Test
    void naoDeveRemoverNomeInexistente() {
        assertFalse(service.removerPorNome(funcionarios, "Zé"));
        assertEquals(7, funcionarios.size());
    }

    @Test
    void deveAplicarAumentoDeDezPorCento() {
        service.aplicarAumento(funcionarios, new BigDecimal("10"));

        // 2009.44 * 1.10 = 2210.384 → 2210.38
        assertEquals(0, new BigDecimal("2210.38").compareTo(funcionarios.get(0).getSalario()));
        // 19119.88 * 1.10 = 21031.868 → 21031.87 (arredonda para cima)
        assertEquals(0, new BigDecimal("21031.87").compareTo(funcionarios.get(3).getSalario()));
    }

    @Test
    void deveAgruparPorFuncao() {
        Map<String, List<Funcionario>> grupos = service.agruparPorFuncao(funcionarios);

        assertEquals(List.of("Coordenador", "Diretor", "Eletricista", "Gerente", "Operador"),
                List.copyOf(grupos.keySet()));
        assertEquals(List.of("Maria", "João", "Heitor"),
                grupos.get("Operador").stream().map(Funcionario::getNome).collect(Collectors.toList()));
    }

    @Test
    void deveFiltrarAniversariantes() {
        List<Funcionario> resultado = service.filtrarAniversariantesDosMeses(funcionarios, Set.of(10, 12));

        assertEquals(List.of("Maria", "Miguel", "Helena"),
                resultado.stream().map(Funcionario::getNome).collect(Collectors.toList()));
    }

    @Test
    void deveEncontrarMaisVelho() {
        Funcionario maisVelho = service.buscarMaisVelho(funcionarios).orElseThrow();

        assertEquals("Caio", maisVelho.getNome());
        assertEquals(65, maisVelho.getIdade(LocalDate.of(2026, 5, 2)));  // no dia do aniversário
        assertEquals(64, maisVelho.getIdade(LocalDate.of(2026, 5, 1)));  // um dia antes
    }

    @Test
    void deveRetornarVazioSemFuncionarios() {
        assertTrue(service.buscarMaisVelho(List.of()).isEmpty());
    }

    @Test
    void deveOrdenarPorNome() {
        List<Funcionario> ordenados = service.ordenarPorNome(funcionarios);

        assertEquals(List.of("Caio", "Heitor", "Helena", "Heloísa", "João", "Maria", "Miguel"),
                ordenados.stream().map(Funcionario::getNome).collect(Collectors.toList()));
        assertEquals("Maria", funcionarios.get(0).getNome()); // original intacta
    }

    @Test
    void deveOrdenarNomeComAcentoInicial() {
        List<Funcionario> lista = List.of(
                new Funcionario("Zeca",   LocalDate.of(1990, 1, 1), BigDecimal.TEN, "Operador"),
                new Funcionario("Álvaro", LocalDate.of(1990, 1, 1), BigDecimal.TEN, "Operador"));

        assertEquals("Álvaro", service.ordenarPorNome(lista).get(0).getNome());
    }

    @Test
    void deveCalcularTotalSalarios() {
        // 2009.44 + 2284.38 + 9836.14 + 19119.88 + 1582.72 + 1606.85 + 2799.93 = 39239.34
        assertEquals(0, new BigDecimal("39239.34").compareTo(service.calcularTotalSalarios(funcionarios)));
    }

    @Test
    void totalDeListaVaziaDeveSerZero() {
        assertEquals(0, BigDecimal.ZERO.compareTo(service.calcularTotalSalarios(List.of())));
    }

    @Test
    void deveCalcularSalariosMinimos() {
        // 19119.88 / 1212.00 = 15.7754... → 15.78
        Funcionario miguel = funcionarios.get(3);
        assertEquals(0, new BigDecimal("15.78").compareTo(service.calcularQuantidadeSalariosMinimos(miguel)));
    }
}
