package model;

import java.time.LocalDate;

public class Professor extends Servidor{

	private static final long serialVersionUID = 1L;
	private boolean coordenador;
	private Disciplina disciplina;

	public Professor(){
		super();
	}
	
	public Professor(String nome, String cpf, String email, Usuario usuario, LocalDate dataNascimento,String siape, boolean coordenador, Disciplina disciplina){
		super(nome,cpf,email,usuario,dataNascimento,siape);
		this.coordenador=coordenador;
		this.cargo = EnumCargo.PROFESSOR;
		this.setDisciplina(disciplina);
	}

	public boolean isCoordenador() {
		return coordenador;
	}

	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
}