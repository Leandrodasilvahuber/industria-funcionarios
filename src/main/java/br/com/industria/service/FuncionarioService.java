package br.com.industria.service;

import br.com.industria.model.Funcionario;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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
}
