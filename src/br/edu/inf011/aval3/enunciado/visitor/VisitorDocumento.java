package br.edu.inf011.aval3.enunciado.visitor;

import br.edu.inf011.aval3.enunciado.model.CPF;
import br.edu.inf011.aval3.enunciado.model.CartaoCredito;
import br.edu.inf011.aval3.enunciado.model.EMail;
import br.edu.inf011.aval3.enunciado.model.RG;

// VISITOR em um VISITOR
public interface VisitorDocumento {
	public Object visit(RG rg);
	public Object visit(CPF cpf);
	public Object visit(EMail email);
	public Object visit(CartaoCredito cartao);
}
