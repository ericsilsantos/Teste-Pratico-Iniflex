package Iniflex;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
	 private BigDecimal salario;
	 private String funcao;
	 public Funcionario(String nome, LocalDate data, BigDecimal salario, String funcao) {
		 super(nome, data);
		 this.salario = salario;
		 this.funcao = funcao;
	 }
	 
	  public String getFuncao() {
		  return funcao;
	  }

	  public BigDecimal getSalario() {
		  return salario;
	  }
	  
	  public void setSalario(BigDecimal aumento) {
		  this.salario = this.salario.add(this.salario.multiply(aumento));
	  }

	  public String showFuncionario() {
		  DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		  DecimalFormat decimalFomart = new DecimalFormat("###,###,###,###.00");
		  return "Nome: "+this.getNome()+
				  "\nData Nascimento: "+ this.diaDeNascimento.format(formatador)+
				  "\nSalario: R$ "+decimalFomart.format(this.salario)+
				  "\nFuncao: "+this.funcao; 
	  }
}
