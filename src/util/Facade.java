package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import dao.AlunoDAO;
import dao.DAOFactory;
import dao.PerfilDAO;
import dao.PessoaDAO;
import dao.ServidorDAO;
import dao.UsuarioDAO;
import model.Aluno;
import model.Pessoa;
import model.Servidor;
import model.Usuario;
import model.Perfil;

public class Facade {

	private Facade() {
		//
	}

	public static String buildToken() {
		int qtdeCaracteres = 60;
		String[] caracteres = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
				"Y", "Z" };
		StringBuilder token = new StringBuilder();
		for (int i = 0; i < qtdeCaracteres; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			token.append(caracteres[posicao]);
		}
		return token.toString();
	}

	public static String getDiretorioPerfilUsuario(int id) {
		Pessoa pessoa = DAOFactory.criarPessoaDAO().buscarPorId(id);
		String imagem = pessoa.getImagem();
		if (imagem != null && !imagem.trim().isEmpty()) {
			return Constantes.getUSER_PROFILE_IMAGES_DIR() + File.separator + imagem;
		} else {
			return Constantes.getUSER_PROFILE_NONE_IMAGE_DIR();
		}
	}

	public static void cadastrarPessoa(Pessoa pessoa, Usuario usuario) {

		PessoaDAO pessoaDAO = DAOFactory.criarPessoaDAO();
		UsuarioDAO usuarioDAO = DAOFactory.criarUsuarioDAO();
		pessoaDAO.cadastrar(pessoa);
		pessoaDAO = dao.DAOFactory.criarPessoaDAO();
		Pessoa p1 = pessoaDAO.buscarPorCpf(pessoa.getCpf());
		usuario.setPessoa(p1);
		usuarioDAO.cadastrar(usuario);

	}

	public static Usuario buscarPorLogin(String login) {
		PessoaDAO pDAO = DAOFactory.criarPessoaDAO();
		Pessoa p = pDAO.buscarPorLogin(login);
		Aluno aluno = DAOFactory.criarAlunoDAO().buscar(p.getId());
		Servidor servidor = DAOFactory.criarServidorDAO().buscar(p.getId());
		if (aluno != null) {
			return aluno.getUsuario();
		} else if (servidor != null) {
			return servidor.getUsuario();
		}

		return null;
	}

	public static Usuario buscarPorMatriculaAndCPF(String matricula, String cpf) {
		PessoaDAO pDAO = DAOFactory.criarPessoaDAO();
		return pDAO.buscarPorMatriculaAndCPF(matricula, cpf).getUsuario();
	}

	public static Usuario buscarPorSiapeAndCPF(String siape, String cpf) {
		PessoaDAO pDao = DAOFactory.criarPessoaDAO();
		return pDao.buscarPorSiapeAndCPF(siape, cpf);
	}

	public static String[] lerArquivoBancoDeDados() {
		String[] bd = new String[3];
		try {
			FileReader arquivo = new FileReader(Constantes.getDatabaseConfDir());
			BufferedReader reader = new BufferedReader(arquivo);
			try {
				bd[0] = reader.readLine();
				bd[1] = reader.readLine();
				bd[2] = reader.readLine();
			} catch (IOException e) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return bd;
	}

	public static String[] lerArquivoEmail() {
		String[] email = new String[2];
		try {
			FileReader arquivo = new FileReader(Constantes.getEmailConfDir());
			BufferedReader reader = new BufferedReader(arquivo);
			try {
				email[0] = reader.readLine();
				email[1] = reader.readLine();
			} catch (IOException e) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return email;

	}

	public static boolean compararNomes(String nome1, String nome2) {
		return nome1.toUpperCase().equals(nome2.toUpperCase());
	}

	public static List<Aluno> buscarAlunos() {
		AlunoDAO aDAO = DAOFactory.criarAlunoDAO();
		return aDAO.listar();
	}

	public static List<Pessoa> buscarPessoasPorNome(String nome, int inicio, int fim) {
		return DAOFactory.criarPessoaDAO().buscarPorNome(nome, inicio, fim);
	}

	public static Integer getQuantidadePessoasPorNome(String nome) {
		return DAOFactory.criarPessoaDAO().getQuantidadePorNome(nome);
	}

	public static List<Aluno> buscarAlunosPorNome(String nome, int inicio, int fim) {
		return DAOFactory.criarAlunoDAO().buscarPorNome(nome, inicio, fim);
	}

	public static Integer getQuantidadeAlunosPorNome(String nome) {
		return DAOFactory.criarAlunoDAO().getQuantidadePorNome(nome);
	}

	public static List<Servidor> buscarServidor() {
		ServidorDAO sDAO = DAOFactory.criarServidorDAO();
		return sDAO.listar();
	}

	public static Aluno buscarAlunoPorID(int id) {
		AlunoDAO aDAO = DAOFactory.criarAlunoDAO();
		return aDAO.buscar(id);
	}

	public static List<Aluno> buscarAlunoPorNome(String nome) {
		AlunoDAO aDAO = DAOFactory.criarAlunoDAO();
		return aDAO.buscarPorNome(nome);
	}

	public static Pessoa buscarPessoaPorId(int id) {
		PessoaDAO pDAO = DAOFactory.criarPessoaDAO();
		return pDAO.buscarPorId(id);
	}

	public static List<Pessoa> buscarPessoaPorNome(String nome) {
		PessoaDAO pDAO = DAOFactory.criarPessoaDAO();
		return pDAO.buscarPorNome(nome);
	}

	public static Perfil buscaPerfilPorId(int id) {
		PerfilDAO pDAO = DAOFactory.criarPerfilDAO();
		return pDAO.buscarPorId(id);
	}

	public static List<Perfil> ListarPeril() {
		PerfilDAO pDAO = DAOFactory.criarPerfilDAO();
		return pDAO.Listar();
	}

	public static void inserirToken(Pessoa pessoa) {
		PessoaDAO pDAO = DAOFactory.criarPessoaDAO();
		pDAO.inserirTokenRecuperacao(pessoa);

	}

	public static Pessoa BuscarEmailVinculado(String email) {
		PessoaDAO pDAO = DAOFactory.criarPessoaDAO();
		return pDAO.buscarPorEmail(email);
	}

	public static List<Pessoa> buscarPessoas() {
		PessoaDAO pDAO = DAOFactory.criarPessoaDAO();
		return pDAO.listar();
	}

	public static List<Servidor> buscarServidorPorNome(String nome) {
		ServidorDAO sDAO = DAOFactory.criarServidorDAO();
		return sDAO.buscarPorNome(nome);
	}

	public static Pessoa verificarTokenRecuperacao(String token) {
		Aluno aluno = DAOFactory.criarAlunoDAO().buscarTokenRecuperacao(token);
		Servidor servidor = DAOFactory.criarServidorDAO().buscarPorToken(token);
		if (aluno != null) {
			return aluno;
		} else if (servidor != null) {
			return servidor;
		} else {
			return null;
		}
	}

	public static Integer getQuantidadeServidoresPorNome(String nome) {
		return DAOFactory.criarServidorDAO().getQuantidadePorNome(nome);
	}

	public static List<Servidor> buscarServidoresPorNome(String nome, Integer inicio, Integer fim) {
		ServidorDAO sDAO = DAOFactory.criarServidorDAO();
		return sDAO.buscarPorNome(nome, inicio, fim);
	}

	public static String converterLocalDateParaString(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return localDate.format(formatter);
	}

	public static LocalDate converterStringParaLocalDate(String data) {

		String[] newDate = data.split("/");
		String[] dataSql = data.split("-");
		if (newDate.length == 3) {
			return LocalDate.of(Integer.valueOf(newDate[2]), Integer.valueOf(newDate[1]), Integer.valueOf(newDate[0]));
		}
		if (dataSql.length == 3) {
			return LocalDate.of(Integer.valueOf(dataSql[0]), Integer.valueOf(dataSql[1]), Integer.valueOf(dataSql[2]));
		} else {
			throw new RuntimeException(
					"Erro: A data de nascimento não está no formato correto, valor informado " + data);
		}

	}
}
