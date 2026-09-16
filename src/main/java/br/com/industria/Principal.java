package br.com.industria;

import br.com.industria.model.Funcionario;
import br.com.industria.service.FuncionarioService;
import br.com.industria.util.FormatadorUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Requisito 3: executa as ações 3.1 a 3.12 na ordem do enunciado. */
public class Principal {

    private static final String FORMATO_LINHA = "%-10s | %-12s | %13s | %-15s%n";
    private static final BigDecimal PERCENTUAL_AUMENTO = new BigDecimal("10");
    private static final Set<Integer> MESES_ANIVERSARIO = Set.of(10, 12);

    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService();

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela
        List<Funcionario> funcionarios = carregarFuncionarios();
        titulo("3.1 – Inserir os funcionários");
        System.out.println(funcionarios.size() + " funcionários inseridos na ordem da tabela.");

        // 3.2 – Remover o funcionário "João" da lista
        titulo("3.2 – Remover o funcionário \"João\"");
        boolean removido = service.removerPorNome(funcionarios, "João");
        System.out.println(removido
                ? "Funcionário João removido. Total de funcionários: " + funcionarios.size()
                : "Funcionário João não encontrado.");

        // 3.3 – Imprimir todos os funcionários com todas as suas informações
        titulo("3.3 – Todos os funcionários");
        imprimirTabela(funcionarios);

        // 3.4 – Aplicar 10% de aumento e atualizar a lista
        titulo("3.4 – Salários após aumento de 10%");
        service.aplicarAumento(funcionarios, PERCENTUAL_AUMENTO);
        imprimirTabela(funcionarios);

        // 3.5 – Agrupar os funcionários por função em um Map
        Map<String, List<Funcionario>> funcionariosPorFuncao = service.agruparPorFuncao(funcionarios);

        // 3.6 – Imprimir os funcionários agrupados por função
        titulo("3.6 – Funcionários agrupados por função");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\n>> " + funcao + " (" + lista.size() + ")");
            lista.forEach(f -> System.out.println("   - " + f.getNome()
                    + " | " + FormatadorUtil.formatarMoeda(f.getSalario())));
        });

        // 3.7 – Item inexistente no enunciado (a numeração vai do 3.6 para o 3.8)

        // 3.8 – Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        titulo("3.8 – Aniversariantes dos meses 10 e 12");
        List<Funcionario> aniversariantes = service.filtrarAniversariantesDosMeses(funcionarios, MESES_ANIVERSARIO);
        if (aniversariantes.isEmpty()) {
            System.out.println("Nenhum funcionário faz aniversário nesses meses.");
        } else {
            aniversariantes.forEach(f -> System.out.println(
                    f.getNome() + " – " + FormatadorUtil.formatarData(f.getDataNascimento())));
        }

        // 3.9 – Imprimir o funcionário com a maior idade (nome e idade)
        titulo("3.9 – Funcionário com a maior idade");
        service.buscarMaisVelho(funcionarios).ifPresentOrElse(
                f -> System.out.println("Nome: " + f.getNome() + " | Idade: " + f.getIdade() + " anos"),
                () -> System.out.println("Nenhum funcionário cadastrado."));

        // 3.10 – Imprimir a lista de funcionários em ordem alfabética
        titulo("3.10 – Funcionários em ordem alfabética");
        imprimirTabela(service.ordenarPorNome(funcionarios));
    }

    /** 3.1 – Cria a lista com os funcionários da tabela, na mesma ordem. */
    static List<Funcionario> carregarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria",   LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"),  "Operador"));
        funcionarios.add(new Funcionario("João",    LocalDate.of(1990, 5, 12),  new BigDecimal("2284.38"),  "Operador"));
        funcionarios.add(new Funcionario("Caio",    LocalDate.of(1961, 5, 2),   new BigDecimal("9836.14"),  "Coordenador"));
        funcionarios.add(new Funcionario("Miguel",  LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice",   LocalDate.of(1995, 1, 5),   new BigDecimal("2234.68"),  "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor",  LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"),  "Operador"));
        funcionarios.add(new Funcionario("Arthur",  LocalDate.of(1993, 3, 31),  new BigDecimal("4071.84"),  "Contador"));
        funcionarios.add(new Funcionario("Laura",   LocalDate.of(1994, 7, 8),   new BigDecimal("3017.45"),  "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24),  new BigDecimal("1606.85"),  "Eletricista"));
        funcionarios.add(new Funcionario("Helena",  LocalDate.of(1996, 9, 2),   new BigDecimal("2799.93"),  "Gerente"));
        return funcionarios;
    }

    /** Imprime a lista em formato de tabela, com data dd/MM/yyyy e valores no padrão brasileiro. */
    private static void imprimirTabela(List<Funcionario> funcionarios) {
        System.out.printf(FORMATO_LINHA, "Nome", "Nascimento", "Salário", "Função");
        System.out.println("-".repeat(60));
        funcionarios.forEach(f -> System.out.printf(FORMATO_LINHA,
                f.getNome(),
                FormatadorUtil.formatarData(f.getDataNascimento()),
                FormatadorUtil.formatarNumero(f.getSalario()),
                f.getFuncao()));
    }

    /** Imprime um cabeçalho para separar visualmente cada requisito. */
    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=".repeat(60));
        System.out.println(texto);
        System.out.println("=".repeat(60));
    }
}
