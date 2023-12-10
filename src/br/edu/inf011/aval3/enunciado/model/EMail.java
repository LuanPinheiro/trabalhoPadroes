package br.edu.inf011.aval3.enunciado.model;

import br.edu.inf011.aval3.enunciado.visitor.VisitorDocumento;

// CONCRETE ELEMENT em um VISITOR
public class EMail implements Documento{
	
	public static final String REGEX = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"; 
	
	public String conta;
	
	public EMail(String conta) {
		super();
		this.conta = conta;
	}
	
	// Métodos de validação, pontuação e formatação movidos para o visitor
	
	public static String getRegex() {
		return REGEX;
	}

	public String getConta() {
		return conta;
	}

	@Override
	public Object accept(VisitorDocumento visitor) {
		return visitor.visit(this);		
	}
}
