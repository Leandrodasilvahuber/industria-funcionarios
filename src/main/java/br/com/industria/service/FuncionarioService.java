package br.com.industria.service;

import br.com.industria.model.Funcionario;

import java.util.List;

/** Regras de negócio sobre a lista de funcionários. */
public class FuncionarioService {

    /** 3.2 – Remove da lista os funcionários com o nome informado. */
    public boolean removerPorNome(List<Funcionario> funcionarios, String nome) {
        return funcionarios.removeIf(f -> f.getNome().equals(nome));
    }
}
