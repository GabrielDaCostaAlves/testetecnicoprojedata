import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        //Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        //Remover o funcionário “João” da lista.
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        //Imprimir todos os funcionários com todas suas informações, sendo que:
        //• informação de data deve ser exibido no formato dd/mm/aaaa;
        //• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        System.out.println("==============================================");
        funcionarios.forEach(System.out::println);

        //Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios.forEach(f -> f.aumentarSalario(new BigDecimal("10")));

        //Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        //Imprimir os funcionários, agrupados por função.
        System.out.println("==============================================");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("---------------------------");
            System.out.println("Função: " + funcao);
            System.out.println("---------------------------");
            lista.forEach(funcionario -> System.out.println("- " + funcionario));
            System.out.println();
        });

        //Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("==============================================");
        System.out.println("Funcionários com aniversário nos meses 10 e 12:");
        System.out.println("---------------------------------------------");

        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(funcionario -> {
                    System.out.println("- " + funcionario);
                });

        //Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        if (maisVelho != null) {
            System.out.println("==============================================");
            System.out.println("Funcionário mais velho: " + maisVelho.getNome() + ", " + maisVelho.getIdade() + " anos");

        }

        //Imprimir a lista de funcionários por ordem alfabética.
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("==============================================");
        System.out.println("Funcionarios em ordem alfabética: ");
        funcionarios.forEach(System.out::println);

        //Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("==============================================");
        System.out.println("Total dos salários: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(totalSalarios));


        //Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("==============================================");
        System.out.println("Funcionários e seus salários em termos de salários mínimos:");
        System.out.println("----------------------------------------------");

        funcionarios.forEach(f -> {
            BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("- %s: %.2f salários mínimos%n", f.getNome(), qtdSalariosMinimos);
        });

        System.out.println("==============================================");
    }


}