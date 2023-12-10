package br.edu.inf011.aval3.enunciado.model;

import br.edu.inf011.aval3.enunciado.visitor.VisitorDocumento;

// CONCRETE ELEMENT em um VISITOR
public class CPF implements Documento{
	
	public String nome;
	public String numero;
	
	
	public CPF(String nome, String numero) {
		super();
		this.nome = nome;
		this.numero = numero;
	}
	
	// Métodos de validação, pontuação e formatação movidos para o visitor
	
	// Visibilidade do método alterada para ser acessada pelo Visitor de validação
	public boolean numeroValido() {
	    int d1, d2;
	    int digito1, digito2, resto;
	    String nDigResult;

	    d1 = d2 = 0;
	    digito1 = digito2 = resto = 0;

	    for (int iCount = this.numero.length() - 1, mult = 2; iCount >= 0; iCount--, mult++) {
	      int digitoCPF = this.numero.charAt(iCount) - '0';
	      d1 += (mult * digitoCPF);
	      d2 += ((mult - 1) * digitoCPF);
	    };

	    resto = (d1 % 11);

	    if (resto < 2)
	      digito1 = 0;
	    else
	      digito1 = 11 - resto;

	    d2 += 2 * digito1;
	    resto = (d2 % 11);

	    if (resto < 2)
	      digito2 = 0;
	    else
	      digito2 = 11 - resto;

	    String nDigVerific = this.numero.substring(this.numero.length() - 2, this.numero.length());
	    nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
	    return nDigVerific.equals(nDigResult);
	  
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
