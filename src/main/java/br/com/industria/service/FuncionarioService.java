package br.com.industria.service;

import br.com.industria.model.Funcionario;

import java.math.BigDecimal;
import java.util.List;

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
}
