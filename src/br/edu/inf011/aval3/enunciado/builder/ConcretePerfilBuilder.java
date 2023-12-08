package br.edu.inf011.aval3.enunciado.builder;

import java.time.LocalDate;

import br.edu.inf011.aval3.enunciado.model.CPF;
import br.edu.inf011.aval3.enunciado.model.Perfil;
import br.edu.inf011.aval3.enunciado.model.RG;

public class ConcretePerfilBuilder implements PerfilBuilder {

	private Perfil perfil;
	private boolean temCpf = false;
	private boolean temRg = false;
	private int qtdCartoes = 0;
	private int qtdEmails = 0;

	@Override
	public void reset() {
		this.perfil = new Perfil(null, null, null);
	}

	@Override
	public void addCpf(String nome, String numero) {
		CPF cpf = new CPF(nome, numero);
		perfil.adicionar(cpf);
	}

	@Override
	public void addRG(String nome, String numero, String expedidor, LocalDate validade) {
		RG rg = new RG(nome, numero, expedidor, validade);
		perfil.adicionar(rg);
	}

	@Override
	public void addCartao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEmail() {
		// TODO Auto-generated method stub
		
	}
}
