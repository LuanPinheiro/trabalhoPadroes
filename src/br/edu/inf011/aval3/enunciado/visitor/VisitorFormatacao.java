package br.edu.inf011.aval3.enunciado.visitor;

import java.time.format.DateTimeFormatter;

import br.edu.inf011.aval3.enunciado.model.CPF;
import br.edu.inf011.aval3.enunciado.model.CartaoCredito;
import br.edu.inf011.aval3.enunciado.model.EMail;
import br.edu.inf011.aval3.enunciado.model.RG;

// CONCRETE VISITOR em um VISITOR
public class VisitorFormatacao implements VisitorDocumento {

	@Override
	public Object visit(RG rg) {
		StringBuilder fmt = new StringBuilder();
		fmt.append("RG\n");
		fmt.append(rg.nome + "\n");
		fmt.append(rg.numero.substring(0, rg.numero.length() - 2) + "-");
		fmt.append(rg.numero.substring(rg.numero.length() - 2, rg.numero.length()) + " - ");
		fmt.append(rg.expedidor + "\n");
		fmt.append("Validade: " + rg.validade.format(DateTimeFormatter.ofPattern("dd/MM/yy")));
		return fmt.toString();
	}

	@Override
	public Object visit(CPF cpf) {
		StringBuilder fmt = new StringBuilder();
		fmt.append("CPF\n");
		fmt.append(cpf.nome + " - ");
		fmt.append(cpf.numero.substring(0, 3) + "." + 
				   cpf.numero.substring(3, 6) + "." +
				   cpf.numero.substring(6, 9) + "-" + 
				   cpf.numero.substring(9, 11));
		return fmt.toString();
	}

	@Override
	public Object visit(EMail email) {
		return "E-MAIL\n" + email.conta;
	}

	@Override
	public Object visit(CartaoCredito cartao) {
		StringBuilder fmt = new StringBuilder();
		fmt.append("CART�O DE CR�DITO\n");
		fmt.append(cartao.nome + "\n");
		fmt.append(cartao.numero.substring(0, 4) + " ");
		fmt.append(cartao.numero.substring(4, 8) + " ");
		fmt.append(cartao.numero.substring(8, 12) + " ");
		fmt.append(cartao.numero.substring(12, 16) + "\n");
		fmt.append(cartao.cvc + "\n");
		fmt.append(cartao.vencimento.format(DateTimeFormatter.ofPattern("MM/yy")));
		return fmt.toString();
	}
	
}
