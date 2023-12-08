package br.edu.inf011.aval3.enunciado.proxy;

import br.edu.inf011.aval3.enunciado.model.Perfil;
import br.edu.inf011.aval3.enunciado.model.service.Classificador;
import br.edu.inf011.aval3.enunciado.model.service.ClassificadorPerfil;
import br.edu.inf011.aval3.enunciado.model.service.NivelPerfil;

// PROXY em um PROXY
// Proxy de proteção
public class ProxyClassificador implements Classificador {

	private ClassificadorPerfil classificadorPerfil;
	private String user;
	private String password;
	
	public ProxyClassificador(ClassificadorPerfil classificadorPerfil, String user, String password) {
		super();
		this.classificadorPerfil = classificadorPerfil;
		this.user = user;
		this.password = password;
	}

	@Override
	public NivelPerfil nivel() {
		Perfil perfil = getPerfil();
		if(perfil.getUser().equals(user) && perfil.getPwd().equals(password)) {
			return classificadorPerfil.nivel();
		}
		
		return NivelPerfil.DESCONHECIDO;
	}

	@Override
	public Perfil getPerfil() {
		return classificadorPerfil.getPerfil();
	}
	
	
}
