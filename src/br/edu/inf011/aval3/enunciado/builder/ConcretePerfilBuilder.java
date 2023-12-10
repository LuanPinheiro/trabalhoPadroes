package br.edu.inf011.aval3.enunciado.builder;

import java.util.ArrayList;
import java.util.List;

import br.edu.inf011.aval3.enunciado.exceptions.DocumentoInvalidoException;
import br.edu.inf011.aval3.enunciado.exceptions.LimiteAtingidoException;
import br.edu.inf011.aval3.enunciado.exceptions.PerfilSemNomeException;
import br.edu.inf011.aval3.enunciado.model.CPF;
import br.edu.inf011.aval3.enunciado.model.CartaoCredito;
import br.edu.inf011.aval3.enunciado.model.Documento;
import br.edu.inf011.aval3.enunciado.model.EMail;
import br.edu.inf011.aval3.enunciado.model.Perfil;
import br.edu.inf011.aval3.enunciado.model.RG;
import br.edu.inf011.aval3.enunciado.visitor.VisitorValidacao;

// CONCRETE BUILDER em um BUILDER
// CLIENT em um VISITOR
public class ConcretePerfilBuilder implements PerfilBuilder {

	private CPF cpf;
	private RG rg;
	private List<CartaoCredito> cartoes;
	private List<EMail> emails;
	private String nome;
	private String user;
	private String password;
	
	public ConcretePerfilBuilder() {
		super();
		this.cpf = null;
		this.rg = null;
		this.cartoes = new ArrayList<CartaoCredito>();
		this.emails = new ArrayList<EMail>();
		this.nome = null;
		this.user = null;
		this.password = null;
	}

	@Override
	public void reset() {
		this.cpf = null;
		this.rg = null;
		this.cartoes = new ArrayList<CartaoCredito>();
		this.emails = new ArrayList<EMail>();
		this.nome = null;
		this.user = null;
		this.password = null;
	}
	
	// Método alterado para chamar Visitor de validação
	@Override
	public void setCpf(String nome, String numero) throws DocumentoInvalidoException {
		CPF cpf = new CPF(nome, numero);
		if(!(boolean) cpf.accept(new VisitorValidacao())) {
			throw new DocumentoInvalidoException("CPF Inválido");
		}
		this.cpf = cpf;
	}

	// Método alterado para chamar Visitor de validação
	@Override
	public void setRg(String nome, String numero, String expedidor, String validade) throws DocumentoInvalidoException {
		RG rg = new RG(nome, numero, expedidor, validade);
		if(!(boolean) rg.accept(new VisitorValidacao())) {
			throw new DocumentoInvalidoException("RG Inválido");
		}
		this.rg = rg;
	}
	
	// Método alterado para chamar Visitor de validação
	@Override
	public void addCartao(String nome, String numero, String cvc, String vencimento) throws LimiteAtingidoException, DocumentoInvalidoException {
		if(cartoes.size() < 3) {
			CartaoCredito cartao = new CartaoCredito(nome, numero, cvc, vencimento);
			if(!(boolean) cartao.accept(new VisitorValidacao())) {
				throw new DocumentoInvalidoException("Cartão Inválido");
			}
			cartoes.add(cartao);
		}
		else
			throw new LimiteAtingidoException("Limite de cartões por conta atingido, máximo 3");
	}

	// Método alterado para chamar Visitor de validação
	@Override
	public void addEmail(String conta) throws LimiteAtingidoException, DocumentoInvalidoException {
		if(emails.size() < 2) {
			EMail email = new EMail(conta);
			if(!(boolean) email.accept(new VisitorValidacao())) {
				throw new DocumentoInvalidoException("Email Inválido");
			}
			emails.add(email);
		}
		else
			throw new LimiteAtingidoException("Limite de emails por conta atingido, máximo 2");
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	public Perfil buildPerfil() throws PerfilSemNomeException {
		if(nome == null) {
			throw new PerfilSemNomeException();
		}
		
		List<Documento> documentos = new ArrayList<Documento>();
		if(cpf != null) {
			documentos.add(cpf);
		}
		if(rg != null) {
			documentos.add(rg);
		}
		for(Documento email : emails) {
			documentos.add(email);
		}
		for(Documento cartao : cartoes) {
			documentos.add(cartao);
		}
		
		this.user = this.user == null ? this.nome : this.user;
		this.password = this.password == null ? this.nome : this.password;
		
		Perfil perfil = new Perfil(this.nome, this.user, this.password, documentos);
		this.reset();
		
		return perfil;
	}
}
