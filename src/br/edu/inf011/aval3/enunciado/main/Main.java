package br.edu.inf011.aval3.enunciado.main;

import br.edu.inf011.aval3.enunciado.builder.ConcretePerfilBuilder;
import br.edu.inf011.aval3.enunciado.exceptions.DocumentoInvalidoException;
import br.edu.inf011.aval3.enunciado.exceptions.LimiteAtingidoException;
import br.edu.inf011.aval3.enunciado.exceptions.PerfilSemNomeException;
import br.edu.inf011.aval3.enunciado.model.Perfil;
import br.edu.inf011.aval3.enunciado.model.service.ClassificadorPerfil;
import br.edu.inf011.aval3.enunciado.proxy.ProxyClassificador;
import br.edu.inf011.aval3.enunciado.visitor.VisitorFormatacao;

// CLIENT em um BUILDER
// CLIENT em um PROXY
public class Main {

	public static void main(String[] args) throws LimiteAtingidoException, PerfilSemNomeException, DocumentoInvalidoException {
		ConcretePerfilBuilder builder = new ConcretePerfilBuilder();
		builder.addCartao("Meu lindo cartão clonado", "5247530203165582", "225", "08/05/2025");
		builder.addCartao("Meu lindo cartão clonado", "5247530203165582", "225", "08/05/2025");
		builder.addCartao("Meu lindo cartão clonado", "5247530203165582", "225", "08/05/2025");
		builder.setNome("Frederico, meu orientador <3");
		Perfil perfil = builder.buildPerfil();
		System.out.println(perfil);
		
		
		ClassificadorPerfil classificador = new ClassificadorPerfil(perfil);
		ProxyClassificador proxy = new ProxyClassificador(classificador, "Frederico, meu orientador <3", "Frederico, meu orientador <3");
		ProxyClassificador proxy2 = new ProxyClassificador(classificador, "Frederico, pior professor", "Frederico, meu orientador <3");
		System.out.println("Nível do perfil: " + proxy.nivel().toString());
		System.out.println("Nível do perfil: " + proxy2.nivel().toString());
		
		VisitorFormatacao formatacao = new VisitorFormatacao();
		perfil.documentos().forEach(d -> System.out.println(d.accept(formatacao)));
	}
}
