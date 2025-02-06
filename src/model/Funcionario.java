package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;


public class Funcionario extends Pessoa{

    private BigDecimal salario;

    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public void aumentarSalario(BigDecimal percentual) {
        BigDecimal aumento = this.salario.multiply(percentual).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        this.salario = this.salario.add(aumento);
    }

    public int getIdade(){
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }

    public String formatarSalario(){
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt","BR"));
        nf.setMinimumFractionDigits(2);
        return nf.format(salario);
    }

    public String formatarDataNascimento(){
        return dataNascimento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Data de Nascimento: %s | Função: %s | Salário: %s",
                nome,
                formatarDataNascimento(),
                funcao,
                formatarSalario());
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public String getNome(){
        return this.nome;
    }
    public LocalDate getDataNascimento(){
        return this.dataNascimento;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(salario, that.salario) && Objects.equals(funcao, that.funcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salario, funcao);
    }
}
