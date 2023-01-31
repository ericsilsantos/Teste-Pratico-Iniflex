package Iniflex;

import java.time.LocalDate;

public class Pessoa {
	String nome;
	LocalDate diaDeNascimento;
	public Pessoa(String nome, LocalDate diaDeNascimento) {
		this.nome = nome;
		this.diaDeNascimento = diaDeNascimento;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public LocalDate getDiaDeNascimento() {
		return this.diaDeNascimento;
	}
}
