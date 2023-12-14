package br.edu.inf011.aval3.enunciado.main;

import br.edu.inf011.aval3.enunciado.builder.ConcretePerfilBuilder;
import br.edu.inf011.aval3.enunciado.exceptions.DocumentoInvalidoException;
import br.edu.inf011.aval3.enunciado.exceptions.LimiteAtingidoException;
import br.edu.inf011.aval3.enunciado.exceptions.PerfilSemNomeException;
import br.edu.inf011.aval3.enunciado.model.Perfil;
import br.edu.inf011.aval3.enunciado.model.service.ClassificadorPerfil;
import br.edu.inf011.aval3.enunciado.proxy.ProxyClassificador;
import br.edu.inf011.aval3.enunciado.visitor.VisitorFormatacao;
import br.edu.inf011.aval3.enunciado.visitor.VisitorPontuacao;
import br.edu.inf011.aval3.enunciado.visitor.VisitorValidacao;

// CLIENT em um BUILDER
// CLIENT em um PROXY
public class Main {

	public static void main(String[] args) throws LimiteAtingidoException, PerfilSemNomeException, DocumentoInvalidoException {
		ConcretePerfilBuilder builder = new ConcretePerfilBuilder();
		builder.addCartao("Meu Cartão", "524 7530203165582", "225", "08/05/2025");
		// Validação de cpf não está funcionando
		builder.addCpf("Meu CPF", "34937999001");
		builder.addRg("Meu RG", "291941448", "SSP", "10/09/2026");
		builder.addEmail("algumemail@gmail.com");
		builder.setNome("Luan");
		builder.setPassword("Camila");
		Perfil perfil = builder.buildPerfil();
		
		System.out.println("****************************\n");
		System.out.println("toString do perfil criado com builder:\n" + perfil + "\n");
		
		VisitorFormatacao formatacao = new VisitorFormatacao();
		VisitorPontuacao pontuacao = new VisitorPontuacao();
		VisitorValidacao validacao = new VisitorValidacao();
		System.out.println("****************************\n");
		System.out.println("Usando visitor de formatação: \n");
		perfil.documentos().forEach(d -> System.out.println(d.accept(formatacao)));
		System.out.println("\n****************************\n");
		System.out.println("Usando visitor de pontuação: \n");
		perfil.documentos().forEach(d -> System.out.println(d.accept(pontuacao)));
		System.out.println("\n****************************\n");
		System.out.println("Usando visitor de validação: \n");
		perfil.documentos().forEach(d -> System.out.println(d.accept(validacao)));
		System.out.println("\n****************************\n");
		
		ClassificadorPerfil classificador = new ClassificadorPerfil(perfil);
		ProxyClassificador proxy = new ProxyClassificador(classificador, "Luan", "Camila");
		ProxyClassificador proxy2 = new ProxyClassificador(classificador, "Camila", "Luan");
		System.out.println("Nível do perfil com credenciais corretas: " + proxy.nivel().toString());
		System.out.println("Nível do perfil com credenciais incorretas: " + proxy2.nivel().toString());
	}
}
