package br.edu.inf011.aval3.enunciado.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.inf011.aval3.enunciado.visitor.VisitorDocumento;

// CONCRETE ELEMENT em um VISITOR
public class RG implements Documento{
	
	// Visibilidade alterada para private
	private String nome;
	private String numero;
	private String expedidor;
	private LocalDate validade;
	
	public RG(String nome, String numero, String expedidor, LocalDate validade) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.expedidor = expedidor;
		this.validade = validade;
	}
	
	// Métodos de validação, pontuação e formatação movidos para o visitor
	
	public RG(String nome, String numero, String expedidor, String validade) {
		this(nome, numero, expedidor, LocalDate.parse(validade, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	public String getExpedidor() {
		return expedidor;
	}

	public LocalDate getValidade() {
		return validade;
	}

	@Override
	public Object accept(VisitorDocumento visitor) {
		return visitor.visit(this);
	}
}
