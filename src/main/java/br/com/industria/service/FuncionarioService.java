package br.com.industria.service;

import br.com.industria.model.Funcionario;
import br.com.industria.util.FormatadorUtil;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/** Regras de negócio sobre a lista de funcionários. */
public class FuncionarioService {

    /** 3.2 – Remove da lista os funcionários com o nome informado. */
    public boolean removerPorNome(List<Funcionario> funcionarios, String nome) {
        return funcionarios.removeIf(f -> f.getNome().equals(nome));
    }

    /** 3.4 – Aplica o mesmo aumento percentual a todos os funcionários da lista. */
    public void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        funcionarios.forEach(f -> f.aplicarAumento(percentual));
    }

    /** 3.5 – Agrupa os funcionários por função, com as chaves em ordem alfabética. */
    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(
                        Funcionario::getFuncao,
                        TreeMap::new,
                        Collectors.toList()));
    }

    /** 3.8 – Retorna os funcionários que fazem aniversário em algum dos meses informados. */
    public List<Funcionario> filtrarAniversariantesDosMeses(List<Funcionario> funcionarios, Set<Integer> meses) {
        return funcionarios.stream()
                .filter(f -> meses.contains(f.getDataNascimento().getMonthValue()))
                .collect(Collectors.toList());
    }

    /** 3.9 – Retorna o funcionário com a maior idade (menor data de nascimento). */
    public Optional<Funcionario> buscarMaisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    /**
     * 3.10 – Retorna uma nova lista ordenada alfabeticamente pelo nome (não altera a original).
     * Usa Collator pt-BR para ordenar nomes acentuados corretamente.
     */
    public List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
        Collator collator = Collator.getInstance(FormatadorUtil.LOCALE_BR);
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome, collator))
                .collect(Collectors.toList());
    }
}
