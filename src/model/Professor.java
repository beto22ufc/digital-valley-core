package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Professor extends Servidor{

	private static final long serialVersionUID = 1L;
	private boolean coordenador;
	private ArrayList<Disciplina>disciplinas;

    public Professor(){
        super();
        this.disciplinas = new ArrayList<Disciplina>();
    }
    
    public Professor(String nome, String cpf, String email, Usuario usuario, LocalDate dataNascimento,String siape, boolean coordenador){
        super(nome,cpf,email,usuario,dataNascimento,siape);
        this.coordenador=coordenador;
        this.cargo = EnumCargo.PROFESSOR;
    }

    public boolean isCoordenador() {
        return coordenador;
    }

    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Disciplina disciplina) {
        if(disciplina==null)
            throw new IllegalArgumentException("Disciplina inválida");
        this.disciplinas.add(disciplina);        
    }
    

}