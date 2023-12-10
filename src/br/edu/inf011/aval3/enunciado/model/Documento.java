package br.edu.inf011.aval3.enunciado.model;

import br.edu.inf011.aval3.enunciado.visitor.VisitorDocumento;

// ELEMENT em um VISITOR
public interface Documento {
	public Object accept(VisitorDocumento visitor);
}
