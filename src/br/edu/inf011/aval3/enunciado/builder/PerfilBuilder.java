package br.edu.inf011.aval3.enunciado.builder;

import br.edu.inf011.aval3.enunciado.exceptions.DocumentoInvalidoException;
import br.edu.inf011.aval3.enunciado.exceptions.LimiteAtingidoException;

// BUILDER em um BUILDER
public interface PerfilBuilder {
	
	public void reset();
	public void setCpf(String nome, String numero) throws DocumentoInvalidoException;
	public void setRg(String nome, String numero, String expedidor, String validade) throws DocumentoInvalidoException;
	public void addCartao(String nome, String numero, String cvc, String vencimento) throws LimiteAtingidoException, DocumentoInvalidoException;
	public void addEmail(String conta) throws LimiteAtingidoException, DocumentoInvalidoException;
	public void setNome(String nome);
	public void setUser(String user);
	public void setPassword(String password);
}
