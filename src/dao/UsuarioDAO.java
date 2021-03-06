package dao;

import model.Usuario;

public interface UsuarioDAO {
	
	
	public void cadastrar(Usuario usuario);
	
	public void editar(Usuario usuario);
	
	public void editarUsuarioESenha(Usuario usuario);
	
	public boolean autenticar(String login, String senha);
	
	public void editarNivel(Usuario usuario);
	
	public void salvarToken(String token, int id_usuario);
	
	public void salvarTokenUsuario(String token, int id_usuario);
	
	public String buscarToken(int id_pessoa);
	
	public String buscarTokenTemp(int id_pessoa);

}