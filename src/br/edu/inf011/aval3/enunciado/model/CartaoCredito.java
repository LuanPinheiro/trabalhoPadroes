package br.edu.inf011.aval3.enunciado.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.inf011.aval3.enunciado.visitor.VisitorDocumento;

// CONCRETE ELEMENT em um VISITOR
public class CartaoCredito implements Documento{
	
	// Visibilidade alterada para private
	private String nome;
	private String numero;	
	private String cvc;
	private LocalDate vencimento;
	
	public CartaoCredito(String nome, String numero, String cvc, LocalDate vencimento) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.cvc = cvc;
		this.vencimento = vencimento;
	}
	
	public CartaoCredito(String nome, String numero, String cvc, String vencimento) {
		this(nome, numero, cvc, LocalDate.parse(vencimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}	

	// Métodos de validação, pontuação e formatação movidos para o visitor
	// Método verificaLuhn movido para VisitorValidacao
	
	// Novo método para acessar as variáveis privadas para limpar a string numero
	public void replaceNumero() {
		numero.replace(" ", "");
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	public String getCvc() {
		return cvc;
	}

	public LocalDate getVencimento() {
		return vencimento;
	}

	@Override
	public Object accept(VisitorDocumento visitor) {
		return visitor.visit(this);
	}	
}

