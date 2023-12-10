package br.edu.inf011.aval3.enunciado.visitor;

import br.edu.inf011.aval3.enunciado.model.CPF;
import br.edu.inf011.aval3.enunciado.model.CartaoCredito;
import br.edu.inf011.aval3.enunciado.model.EMail;
import br.edu.inf011.aval3.enunciado.model.RG;

//CONCRETE VISITOR em um VISITOR
public class VisitorPontuacao implements VisitorDocumento {

	@Override
	public Object visit(RG rg) {
		return (boolean) rg.accept(new VisitorValidacao()) ? 1 : 0;
	}

	@Override
	public Object visit(CPF cpf) {
		return 3;
	}

	@Override
	public Object visit(EMail email) {
		return (boolean) email.accept(new VisitorValidacao()) ? 1 : 0;
	}

	@Override
	public Object visit(CartaoCredito cartao) {
		return (boolean) cartao.accept(new VisitorValidacao()) ? 2 : 0;
	}
}
