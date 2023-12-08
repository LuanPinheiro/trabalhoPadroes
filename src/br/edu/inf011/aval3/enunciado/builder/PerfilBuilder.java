package br.edu.inf011.aval3.enunciado.builder;

import java.time.LocalDate;

// BUILDER em um BUILDER
public interface PerfilBuilder {
	
	public void reset();
	public void addCpf(String nome, String numero);
	public void addRG(String nome, String numero, String expedidor, LocalDate validade);
	public void addCartao();
	public void addEmail();
}
