package util;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import model.Aluno;
import model.EnumNivel;
import model.Pessoa;
import model.Usuario;

public class FacadeTest {

	Pessoa pessoa = new Pessoa();
	Usuario usuario = new Usuario();

	@Ignore
	@Test
	public void cadatrarPessoaTest() {
		pessoa.setNome("Fachada Test");
		pessoa.setCpf("12345378145");
		pessoa.setEmail("fachada31@email.com");
		pessoa.setDataNascimento("01/02/1990");
		usuario.setLogin("Login1s");
		usuario.setSenha("Senha123");
		usuario.setNivel(EnumNivel.ADMINISTRADOR);
		pessoa.setUsuario(usuario);
		usuario.setPessoa(pessoa);

		util.Facade.cadastrarPessoa(pessoa, usuario);

	}

	

	@Ignore
	@Test
	public void testListarAluno() {
		List<Aluno> alunos = util.Facade.buscarAlunos();
		for (Aluno a : alunos) {
			System.out.println(a.getNome());
			System.out.println(a.getUsuario().getLogin());
			System.out.println(a.getUsuario().getNivel());
		}
	}



	@Ignore
	@Test
	public void testLerEmail() {
		String[] email = util.Facade.lerArquivoEmail();
		System.out.println(email[0]);
		System.out.println(email[1]);
	}
	@Ignore
	@Test
	public void testdigito() {
		String matricula = "123s56";
		if(matricula.matches("^[0-9]+$")) {
			System.out.println("Só numeros");
		}else {
			System.out.println("tem letras");
		}
	}
	
	
	@Test
	public void crypter() {
		System.out.println(Crypter.crypt("deyvison123"));
	}


	

}