package br.edu.inf011.aval3.enunciado.model.service;

import java.util.Iterator;

import br.edu.inf011.aval3.enunciado.model.CPF;
import br.edu.inf011.aval3.enunciado.model.CartaoCredito;
import br.edu.inf011.aval3.enunciado.model.Documento;
import br.edu.inf011.aval3.enunciado.model.Perfil;
import br.edu.inf011.aval3.enunciado.visitor.VisitorPontuacao;
import br.edu.inf011.aval3.enunciado.visitor.VisitorValidacao;

// REAL SUBJECT em um PROXY
public class ClassificadorPerfil implements Classificador{
	
	private Perfil perfil;
	
	public ClassificadorPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	// Método alterado para chamar Visitors de validação e pontuação 
	public NivelPerfil nivel() {
		boolean hasCPF = false;
		Integer qtdeCC = 0;
		
		
		Integer pontuacao = perfil.documentos()
				                  .filter(doc-> (boolean) doc.accept(new VisitorValidacao()))
				                  .mapToInt(doc-> (Integer) doc.accept(new VisitorPontuacao()))
				                  .sum();
		Iterator<Documento> docs = perfil.documentos().iterator();
		while(docs.hasNext()) {
			Documento doc = docs.next();
			if(doc.getClass().equals(CPF.class))
				hasCPF = true;
			else if(doc.getClass().equals(CartaoCredito.class))
				qtdeCC++;
		}
		
		if(hasCPF && pontuacao >= 5)
			return NivelPerfil.OURO;
		else if(qtdeCC >= 1 && pontuacao >=5)			
			return NivelPerfil.PRATA;
		else if(pontuacao >=3)
			return NivelPerfil.BRONZE;
		return NivelPerfil.NAO_VERIFICADO;
	}

	@Override
	public Perfil getPerfil() {
		return this.perfil ;
	}
}
