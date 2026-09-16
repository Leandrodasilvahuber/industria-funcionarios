package br.com.industria.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/** Requisito 2: funcionário, com salário e função além dos dados herdados de Pessoa. */
public class Funcionario extends Pessoa {

    private static final int ESCALA_MONETARIA = 2;
    private static final BigDecimal CEM = new BigDecimal("100");

    private BigDecimal salario;
    private final String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);

        Objects.requireNonNull(salario, "O salário é obrigatório");
        Objects.requireNonNull(funcao, "A função é obrigatória");

        if (salario.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O salário não pode ser negativo");
        }
        if (funcao.isBlank()) {
            throw new IllegalArgumentException("A função não pode estar em branco");
        }

        this.salario = salario.setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP);
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    /** Aplica um aumento percentual ao salário (usado no requisito 3.4). */
    public void aplicarAumento(BigDecimal percentual) {
        Objects.requireNonNull(percentual, "O percentual é obrigatório");
        if (percentual.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O percentual de aumento não pode ser negativo");
        }

        BigDecimal fator = BigDecimal.ONE.add(percentual.divide(CEM));
        this.salario = this.salario.multiply(fator).setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Funcionario{nome='" + getNome() + "', dataNascimento=" + getDataNascimento()
                + ", salario=" + salario + ", funcao='" + funcao + "'}";
    }
}
