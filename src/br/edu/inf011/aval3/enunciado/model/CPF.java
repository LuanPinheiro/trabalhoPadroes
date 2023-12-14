package br.edu.inf011.aval3.enunciado.model;

import br.edu.inf011.aval3.enunciado.visitor.VisitorDocumento;

// CONCRETE ELEMENT em um VISITOR
public class CPF implements Documento{
	
	// Visibilidade alterada para private
	private String nome;
	private String numero;
	
	public CPF(String nome, String numero) {
		super();
		this.nome = nome;
		this.numero = numero;
	}
	
	// Métodos de validação, pontuação e formatação movidos para o visitor
	// Método numeroValido movido para VisitorValidacao
	
	// Novo método para acessar as variáveis privadas para limpar a string numero
	public void replaceNumero() {
		numero.replace(".", "");
		numero.replace("-", "");
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	@Override
	public Object accept(VisitorDocumento visitor) {
		return visitor.visit(this);
	}
}
