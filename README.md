# Teste Prático – Funcionários da Indústria

Aplicação Java de console que cadastra os funcionários de uma indústria e executa as operações pedidas no teste (remoção, aumento salarial, agrupamento por função, filtros, ordenação e totais).

## Tecnologias

- Java 17 (compatível com versões mais novas, como a 21)
- Maven para build e dependências
- JUnit 5 para testes automatizados

## Estrutura

```
src/
├── main/java/br/com/industria/
│   ├── Principal.java                 → executa os requisitos 3.1 a 3.12
│   ├── model/
│   │   ├── Pessoa.java                → requisito 1 (nome, dataNascimento)
│   │   └── Funcionario.java           → requisito 2 (extends Pessoa: salario, funcao)
│   ├── service/
│   │   └── FuncionarioService.java    → regras de negócio
│   └── util/
│       └── FormatadorUtil.java        → datas dd/MM/yyyy e números 1.234,56
└── test/java/br/com/industria/        → testes JUnit 5
```

## Como executar

Pré-requisitos: JDK 17+ e Maven 3.8+.

```bash
mvn compile exec:java   # roda a aplicação
mvn test                 # roda os testes
mvn package && java -jar target/industria-funcionarios-1.0.0.jar
```

Pela IDE: abra a pasta como projeto Maven e execute a classe `br.com.industria.Principal`.

> **Windows:** se os acentos aparecerem errados no terminal, execute `chcp 65001` antes de rodar.

## Decisões técnicas

| Decisão | Motivo |
|---|---|
| `BigDecimal` para salários, a partir de `String` | `double` não representa centavos com exatidão |
| `RoundingMode.HALF_UP` com 2 casas | Arredondamento monetário previsível |
| Escala definida na divisão (3.12) | `divide` sem escala lança `ArithmeticException` em dízimas |
| `LocalDate` e `DateTimeFormatter` | API imutável e thread-safe |
| `DecimalFormat` com símbolos pt-BR | Milhar com ponto e decimal com vírgula |
| `removeIf` (3.2) | Remove sem `ConcurrentModificationException` |
| `groupingBy` com `TreeMap` (3.5) | Funções exibidas em ordem alfabética |
| Mais velho = menor data de nascimento (3.9) | Mais preciso que comparar idades em anos |
| `Collator` pt-BR (3.10) | Ordena nomes acentuados corretamente |
| Regras no `FuncionarioService` | Separação de responsabilidades e testabilidade |
| Validações nos construtores | Objetos nunca ficam em estado inválido |

## Observações sobre o enunciado

- O enunciado não possui o item **3.7** (vai do 3.6 para o 3.8). A numeração original foi mantida.
- O item **3.12** usa os salários já com o aumento do item 3.4.
- Nenhum funcionário da tabela nasceu em dezembro, por isso o item **3.8** lista apenas os de outubro.
- A idade no item **3.9** é calculada a partir da data atual de execução.

## Histórico de desenvolvimento

O projeto foi construído em commits incrementais, seguindo a ordem do enunciado (setup → classes de domínio → utilitário de formatação → cada requisito 3.1 a 3.12). Ver `git log` para o histórico completo.

## Saída da execução

Exemplo gerado em 16/09/2026:

```

============================================================
3.1 – Inserir os funcionários
============================================================
10 funcionários inseridos na ordem da tabela.

============================================================
3.2 – Remover o funcionário "João"
============================================================
Funcionário João removido. Total de funcionários: 9

============================================================
3.3 – Todos os funcionários
============================================================
Nome       | Nascimento   |       Salário | Função         
------------------------------------------------------------
Maria      | 18/10/2000   |      2.009,44 | Operador       
Caio       | 02/05/1961   |      9.836,14 | Coordenador    
Miguel     | 14/10/1988   |     19.119,88 | Diretor        
Alice      | 05/01/1995   |      2.234,68 | Recepcionista  
Heitor     | 19/11/1999   |      1.582,72 | Operador       
Arthur     | 31/03/1993   |      4.071,84 | Contador       
Laura      | 08/07/1994   |      3.017,45 | Gerente        
Heloísa    | 24/05/2003   |      1.606,85 | Eletricista    
Helena     | 02/09/1996   |      2.799,93 | Gerente        

============================================================
3.4 – Salários após aumento de 10%
============================================================
Nome       | Nascimento   |       Salário | Função         
------------------------------------------------------------
Maria      | 18/10/2000   |      2.210,38 | Operador       
Caio       | 02/05/1961   |     10.819,75 | Coordenador    
Miguel     | 14/10/1988   |     21.031,87 | Diretor        
Alice      | 05/01/1995   |      2.458,15 | Recepcionista  
Heitor     | 19/11/1999   |      1.740,99 | Operador       
Arthur     | 31/03/1993   |      4.479,02 | Contador       
Laura      | 08/07/1994   |      3.319,20 | Gerente        
Heloísa    | 24/05/2003   |      1.767,54 | Eletricista    
Helena     | 02/09/1996   |      3.079,92 | Gerente        

============================================================
3.6 – Funcionários agrupados por função
============================================================

>> Contador (1)
   - Arthur | R$ 4.479,02

>> Coordenador (1)
   - Caio | R$ 10.819,75

>> Diretor (1)
   - Miguel | R$ 21.031,87

>> Eletricista (1)
   - Heloísa | R$ 1.767,54

>> Gerente (2)
   - Laura | R$ 3.319,20
   - Helena | R$ 3.079,92

>> Operador (2)
   - Maria | R$ 2.210,38
   - Heitor | R$ 1.740,99

>> Recepcionista (1)
   - Alice | R$ 2.458,15

============================================================
3.8 – Aniversariantes dos meses 10 e 12
============================================================
Maria – 18/10/2000
Miguel – 14/10/1988

============================================================
3.9 – Funcionário com a maior idade
============================================================
Nome: Caio | Idade: 65 anos

============================================================
3.10 – Funcionários em ordem alfabética
============================================================
Nome       | Nascimento   |       Salário | Função         
------------------------------------------------------------
Alice      | 05/01/1995   |      2.458,15 | Recepcionista  
Arthur     | 31/03/1993   |      4.479,02 | Contador       
Caio       | 02/05/1961   |     10.819,75 | Coordenador    
Heitor     | 19/11/1999   |      1.740,99 | Operador       
Helena     | 02/09/1996   |      3.079,92 | Gerente        
Heloísa    | 24/05/2003   |      1.767,54 | Eletricista    
Laura      | 08/07/1994   |      3.319,20 | Gerente        
Maria      | 18/10/2000   |      2.210,38 | Operador       
Miguel     | 14/10/1988   |     21.031,87 | Diretor        

============================================================
3.11 – Total dos salários
============================================================
Total: R$ 50.906,82

============================================================
3.12 – Quantidade de salários mínimos (R$ 1.212,00)
============================================================
Maria      |   R$ 2.210,38 | 1,82 salários mínimos
Caio       |  R$ 10.819,75 | 8,93 salários mínimos
Miguel     |  R$ 21.031,87 | 17,35 salários mínimos
Alice      |   R$ 2.458,15 | 2,03 salários mínimos
Heitor     |   R$ 1.740,99 | 1,44 salários mínimos
Arthur     |   R$ 4.479,02 | 3,70 salários mínimos
Laura      |   R$ 3.319,20 | 2,74 salários mínimos
Heloísa    |   R$ 1.767,54 | 1,46 salários mínimos
Helena     |   R$ 3.079,92 | 2,54 salários mínimos
```
