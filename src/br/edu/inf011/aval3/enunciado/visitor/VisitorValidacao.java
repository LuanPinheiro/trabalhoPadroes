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
		return rg.getValidade().isAfter(LocalDate.now());
	}

	@Override
	public Object visit(CPF cpf) {
		cpf.replaceNumero();
		
		return this.numeroValido(cpf);
	}

	@Override
	public Object visit(EMail email) {
		return Pattern.compile(EMail.getRegex()).matcher(email.getConta()).matches();
	}

	@Override
	public Object visit(CartaoCredito cartao) {
		cartao.replaceNumero();
		
		return this.verificaLuhn(cartao) && cartao.getVencimento().isAfter(LocalDate.now());
	}
	
	private boolean numeroValido(CPF cpf) {
		int d1, d2;
	    int digito1, digito2, resto;
	    String nDigResult;
		
	    d1 = d2 = 0;
	    digito1 = digito2 = resto = 0;

	    for (int iCount = cpf.getNumero().length() - 3, mult = 2; iCount >= 0; iCount--, mult++) {
	      int digitoCPF = cpf.getNumero().charAt(iCount) - '0';
	      d1 += (mult * digitoCPF);
	      d2 += ((mult + 1) * digitoCPF);
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

	    String nDigVerific = cpf.getNumero().substring(cpf.getNumero().length() - 2, cpf.getNumero().length());
	    nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
	    return nDigVerific.equals(nDigResult);
	}
	
	private boolean verificaLuhn(CartaoCredito cartao) {
		int sum = 0;
		boolean shouldDouble = false;
		for (int iCont = cartao.getNumero().length() - 1; iCont >= 0; iCont--) {
			int digit = cartao.getNumero().charAt(iCont) - '0';
		    if (shouldDouble) {
		      if ((digit *= 2) > 9) digit -= 9;
		    }
		    sum += digit;
		    shouldDouble = !shouldDouble;
		  }
		  return (sum % 10) == 0;		
	}
}
