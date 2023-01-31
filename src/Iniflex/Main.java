package Iniflex;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	private static Funcionario funcComMaiorIdade = new Funcionario("", LocalDate.now(), BigDecimal.valueOf(0), "");
	private static BigDecimal somatoria = new BigDecimal(0); 
	private static String menu = "----------Menu-----------"+
	"\n1 - Remover o funcionário João"+
	"\n2 - Mostrar todos os funcionários"+
	"\n3 - Aumentar o salário de todos os funcionários"+
	"\n4 - Agrupar os funcionários de acordo com o cargo"+
	"\n5 - Mostrar funcionários que faz aniversário no mes 10 e 12"+
	"\n6 - Mostrar o funcionário com maior idade"+
	"\n7 - Mostrar os funcionários em ordem alfabética"+
	"\n8 - Mostrar a soma de todos os salários"+
	"\n9 - Mostrar quantos salários mínimos cada funcionário recebe"+
	"\n0 - Sair"+
	"\nEscolha um número:";
	
	public static ArrayList<Funcionario> criarListaDeFuncionarios() {
		ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
		lista.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44),"Operador"));
		lista.add(new Funcionario("João", LocalDate.of(1990, 05, 12), BigDecimal.valueOf(2284.38),"Operador"));
		lista.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), BigDecimal.valueOf(9836.14),"Coordenador"));
		lista.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88),"Diretor"));
		lista.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), BigDecimal.valueOf(2234.68),"Recepcionista"));
		lista.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72),"Operador"));
		lista.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), BigDecimal.valueOf(4071.84),"Contador"));
		lista.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), BigDecimal.valueOf(3017.45),"Gerente"));
		lista.add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), BigDecimal.valueOf(1606.85),"Eletricista"));
		lista.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), BigDecimal.valueOf(2799.93),"Gerente"));
		return lista;
	}
	
	public static Map<String, ArrayList<String>> criarMapaDeFuncionarios(ArrayList<Funcionario> lista) {
		Map<String, ArrayList<String>> mapa = new HashMap<String, ArrayList<String>>();
        lista.forEach(func -> {
            ArrayList<String> funcao = new ArrayList<String>();
            if (mapa.get(func.getFuncao()) != null) {
                funcao = mapa.get(func.getFuncao());
            } 
            funcao.add(func.getNome());
            mapa.put(func.getFuncao(), funcao);
        });
		return mapa;
	}
	
	public static void removerFuncionario(ArrayList<Funcionario> lista) {
		for (int index = 0; index < lista.size(); index++) {
			if(lista.get(index).getNome().equals("João")) {
				lista.remove(index);
			}
		}
	}
	
	public static void mostrarFuncionarios(ArrayList<Funcionario> lista) {
		lista.forEach(funcionario -> {
			System.out.println(funcionario.showFuncionario());
			System.out.println("------------------------------------------");
		});
	}
	
	public static void aumentarSalario(ArrayList<Funcionario> lista) {
		BigDecimal aumento = BigDecimal.valueOf(0.1);
		lista.forEach(funcionario -> funcionario.setSalario(aumento));
		System.out.println("Aumento realizado");
	}
	
	public static void mostrarMapaDeFuncionarios(Map<String, ArrayList<String>> mapa) {
		mapa.forEach((cargo, funcionarios) -> {
			System.out.println(cargo+":");
			funcionarios.forEach(func -> {
				System.out.println(func);
			});
			System.out.println("----------------------------");
		});
	}
	
	public static void imprimirAniverariantesDoMes(ArrayList<Funcionario> lista) {
		lista.forEach(funcionario -> {
			if(funcionario.diaDeNascimento.getMonthValue() == 10 || funcionario.diaDeNascimento.getMonthValue() == 12) {
				System.out.println(funcionario.showFuncionario());
			}
		});
	}
	
	public static void maiorIdadeFuncionario(ArrayList<Funcionario> lista) {
		lista.forEach(funcionario -> {
			int comparacao = funcComMaiorIdade.getDiaDeNascimento().compareTo(funcionario.getDiaDeNascimento());
			if (comparacao > 0) {
				funcComMaiorIdade = funcionario;
			}
		});
		int idade = Period.between(funcComMaiorIdade.getDiaDeNascimento(), LocalDate.now()).getYears();
		System.out.println("Nome: "+funcComMaiorIdade.getNome());
		System.out.println("Idade: "+idade+" anos");
	}
	
	public static void ordemAlfabeticaFuncionarios(ArrayList<Funcionario> lista) {
		lista.sort(Comparator.comparing(Funcionario::getNome));
	}
	
	public static void somatoriaDosSalarios(ArrayList<Funcionario> lista) {
		DecimalFormat decimalFomart = new DecimalFormat("###,###,###,###.00");
		lista.forEach(funcionario -> somatoria = somatoria.add(funcionario.getSalario()));
		System.out.println("A somatória de todos os salários: R$ "+decimalFomart.format(somatoria));
	}
	
	public static void salarioMinimoPorFuncionario(ArrayList<Funcionario> lista) {
		BigDecimal salMinimo = new BigDecimal(1212);
		lista.forEach(funcionario -> {
			System.out.println("Nome: " + funcionario.getNome());
			System.out.println("Salários mínimos: " + funcionario.getSalario().divide(salMinimo,2,RoundingMode.UP));
			System.out.println("----------------------------------------------");
		});
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		boolean repetir = true;
		// 3.1 - criando a lista de funcionarios na ordem da tabela
		ArrayList<Funcionario> listaDeFuncionarios = criarListaDeFuncionarios();
		// 3.5 - Agrupar os funcionarios por função em um MAP
		Map<String, ArrayList<String>> mapaDeFuncionarios = criarMapaDeFuncionarios(listaDeFuncionarios);
		Scanner input = new Scanner(System.in);
		while (repetir) {			
			System.out.println(menu);    
			String opcao = input.nextLine();
			switch(opcao) {
				case "1":
					// 3.2 Remover o funcionário João
					removerFuncionario(listaDeFuncionarios);
					break;
				case "2":
					// 3.3 Imprimir todos os funcionarios e suas informações
					mostrarFuncionarios(listaDeFuncionarios);
					break;
				case "3":
					// 3.4 Aumentar os salários em 10%
					aumentarSalario(listaDeFuncionarios);
					break;
				case "4":
					// 3.6 Imprimir o MAP dos funcionarios
					mostrarMapaDeFuncionarios(mapaDeFuncionarios);
					break;
				case "5":
					// 3.8 Imprimir funcionario que faz aniverio no mes 10 e 12
					imprimirAniverariantesDoMes(listaDeFuncionarios);
					break;
				case "6":
					// 3.9 Imprimir funcionario com maior idade
					maiorIdadeFuncionario(listaDeFuncionarios);
					break;
				case "7":
					// 3.10 Ordenar e imprimir a lista de funcionarios
					ordemAlfabeticaFuncionarios(listaDeFuncionarios);
					mostrarFuncionarios(listaDeFuncionarios);
					break;
				case "8":
					// 3.11 Imprimir a somatoria dos salarios
					somatoriaDosSalarios(listaDeFuncionarios);
					break;
				case "9":
					// 3.12 Imprimir os sálarios mínimos por funcionário
					salarioMinimoPorFuncionario(listaDeFuncionarios);
					break;
				case "0":
					System.out.println("Até mais!!");
					repetir = false;
					break;
				default:
					System.out.println("Opção incorreta");
			}
		}
		input.close();
	}

}