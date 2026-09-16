package br.com.industria.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/** Requisito 1: pessoa com nome e data de nascimento. */
public class Pessoa {

    private final String nome;
    private final LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        Objects.requireNonNull(nome, "O nome é obrigatório");
        Objects.requireNonNull(dataNascimento, "A data de nascimento é obrigatória");

        if (nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode estar em branco");
        }
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode estar no futuro");
        }

        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /** Idade em anos completos na data de referência informada. */
    public int getIdade(LocalDate dataReferencia) {
        return Period.between(dataNascimento, dataReferencia).getYears();
    }

    public int getIdade() {
        return getIdade(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Pessoa{nome='" + nome + "', dataNascimento=" + dataNascimento + "}";
    }
}
