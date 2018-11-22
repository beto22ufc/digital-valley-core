package model;

import java.io.Serializable;

import util.Crypter;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	private Pessoa pessoa;
	private EnumNivel nivel;
	private EnumPerfil perfil;
	private String token;
	private String tokenUsuario;

	public void setToken(String token) {
		this.token = token;
	}
	public Usuario(){
		
	}
	public Usuario(String login, String senha, Pessoa pessoa) {
		super();
		this.login = login;
		this.setSenha(senha);
		this.nivel = EnumNivel.COMUM;
		this.pessoa = pessoa;
	}
	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.setSenha(senha);
		this.nivel = EnumNivel.COMUM;
		this.perfil = EnumPerfil.VISITANTE;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if(login != null &&login.length() >= 4){
			this.login = login;
		}else{
			throw new IllegalArgumentException("Erro: O valor do login não pode ser nulo e deve possuir pelo menos 4 caracteres, valor informado: "+login);
		}
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if(senha != null && senha.length() >= 6){
			this.senha = senha;
		}else{
			throw new IllegalArgumentException("Erro: O valor da senha não pode ser nulo e deve possuir pelo menos 6 caracteres, valor informado: "+senha);
		}
	}
	public EnumNivel getNivel() {
		return nivel;
	}
	
	public EnumPerfil getPerfil() {
		return perfil;
	}
	public void setPerfil(EnumPerfil perfil) {
		if(perfil != null) {
			this.perfil = perfil;
		}else {
			throw new IllegalArgumentException("Erro: O valor do perfil não pode ser nulo, valor informado: "+perfil);
		}
	}
	
	public void setPerfil(int perfil) {
		switch (perfil) {
		case 1:
			this.perfil = EnumPerfil.ADMINISTRADOR; 
			break;
		case 2:
			this.perfil = EnumPerfil.ALUNO;
			break;
		case 3:
			this.perfil = EnumPerfil.SERVIDOR;
			break;	
		case 4:
			this.perfil = EnumPerfil.VISITANTE;
			break;
		default:
			throw new IllegalArgumentException("Erro: O valor do perfil não pode ser nulo ou invalido, valor informado: "+perfil);
			
		}
	}
	
	
	
	public void setNivel(EnumNivel nivel) {
		this.nivel = nivel;
	}
	
	public void setNivel(int nivel){
		switch (nivel) {
		case 1:
			this.nivel = EnumNivel.ADMINISTRADOR; 
			break;
		case 2:
			this.nivel = EnumNivel.COMUM;
			break;
		default:
			throw new IllegalArgumentException("Erro: O valor do nivel não pode ser nulo ou invalido, valor informado: "+nivel);
			
		}
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public String getToken() {
		return token;
	}
	public String getTokenUsuario() {
		return tokenUsuario;
	}
	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}
}
