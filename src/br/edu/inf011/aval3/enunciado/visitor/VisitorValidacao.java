package br.edu.inf011.aval3.enunciado.visitor;

import java.time.LocalDate;
import java.util.regex.Pattern;

import br.edu.inf011.aval3.enunciado.model.CPF;
import br.edu.inf011.aval3.enunciado.model.CartaoCredito;
import br.edu.inf011.aval3.enunciado.model.EMail;
import br.edu.inf011.aval3.enunciado.model.RG;

//CONCRETE VISITOR em um VISITOR
public class VisitorValidacao implements VisitorDocumento {

	@Override
	public Object visit(RG rg) {
		return rg.validade.isAfter(LocalDate.now());
	}

	@Override
	public Object visit(CPF cpf) {
		cpf.numero.replace(".", "");
		cpf.numero.replace("-", "");
		
		return cpf.numeroValido();
	}

	@Override
	public Object visit(EMail email) {
		return Pattern.compile(EMail.REGEX).matcher(email.conta).matches();
	}

	@Override
	public Object visit(CartaoCredito cartao) {
		cartao.numero.replace(" ", "");
		return cartao.verificaLuhn() && cartao.vencimento.isAfter(LocalDate.now());
	}
}
