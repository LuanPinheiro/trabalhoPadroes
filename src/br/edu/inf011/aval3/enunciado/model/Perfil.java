package br.edu.inf011.aval3.enunciado.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import br.edu.inf011.aval3.enunciado.visitor.VisitorFormatacao;
import br.edu.inf011.aval3.enunciado.visitor.VisitorValidacao;

// PRODUCT em um BUILDER
// OBJECT STRUCTURE em um VISITOR
public class Perfil {
	
	private List<Documento> documentos;
	private String nome;
	private String user;
	private String pwd;
	
	public Perfil(String nome, String user, String pwd) {
		this(nome, user, pwd, new LinkedList<Documento>());
	}
	
	public Perfil(String nome, String user, String pwd, List<Documento> documentos) {
		this.documentos = documentos;
		this.nome = nome;
		this.user = user;
		this.pwd = pwd;
	}
	
	public void adicionar(Documento documento) {
		this.documentos.add(documento);
	}

	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.nome + "\n");
		for(Documento doc : this.documentos)
			str.append(doc.accept(new VisitorFormatacao()) + ((boolean) doc.accept(new VisitorValidacao()) ? " [Válido]" : "") + "\n");
		return str.toString();
	}

	public String getUser() {
		return user;
	}

	public String getPwd() {
		return pwd;
	}

	public Stream<Documento> documentos(){
		return this.documentos.stream();
	}
}
