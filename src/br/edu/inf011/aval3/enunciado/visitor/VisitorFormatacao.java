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
		fmt.append(rg.getNome() + "\n");
		fmt.append(rg.getNumero().substring(0, rg.getNumero().length() - 2) + "-");
		fmt.append(rg.getNumero().substring(rg.getNumero().length() - 2, rg.getNumero().length()) + " - ");
		fmt.append(rg.getExpedidor() + "\n");
		fmt.append("Validade: " + rg.getValidade().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
		return fmt.toString();
	}

	@Override
	public Object visit(CPF cpf) {
		StringBuilder fmt = new StringBuilder();
		fmt.append("CPF\n");
		fmt.append(cpf.getNome() + " - ");
		fmt.append(cpf.getNumero().substring(0, 3) + "." + 
				   cpf.getNumero().substring(3, 6) + "." +
				   cpf.getNumero().substring(6, 9) + "-" + 
				   cpf.getNumero().substring(9, 11));
		return fmt.toString();
	}

	@Override
	public Object visit(EMail email) {
		return "E-MAIL\n" + email.getConta();
	}

	@Override
	public Object visit(CartaoCredito cartao) {
		StringBuilder fmt = new StringBuilder();
		fmt.append("CART�O DE CR�DITO\n");
		fmt.append(cartao.getNome() + "\n");
		fmt.append(cartao.getNumero().substring(0, 4) + " ");
		fmt.append(cartao.getNumero().substring(4, 8) + " ");
		fmt.append(cartao.getNumero().substring(8, 12) + " ");
		fmt.append(cartao.getNumero().substring(12, 16) + "\n");
		fmt.append(cartao.getCvc() + "\n");
		fmt.append(cartao.getVencimento().format(DateTimeFormatter.ofPattern("MM/yy")));
		return fmt.toString();
	}
	
}
