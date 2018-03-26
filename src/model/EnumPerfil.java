package model;

public enum EnumPerfil {
	ADMINISTRADOR(1), ALUNO(2), SERVIDOR(3), VISITANTE(4);

	private int valorPerfil;

	EnumPerfil(int valor) {
		valorPerfil = valor;
	}

	public int getValorPerfil() {
		return valorPerfil;
	}
	
	public static EnumPerfil getByString(String value){
		switch (value.toUpperCase()) {
		case "ADMINISTRADOR":
			return EnumPerfil.ADMINISTRADOR;
		case "ALUNO":
			return EnumPerfil.ALUNO;
		case "SERVIDOR":
			return EnumPerfil.SERVIDOR;
		case "VISITANTE":
			return EnumPerfil.VISITANTE;

		default:
			return null;
			
		}
	}
	
	
	public static String value(Integer valor){
		switch(valor){
		case 1:
			return "Administrador";
		case 2:
			return "Aluno";
		case 3:
			return "Servidor";
		case 4:
			return "Visitante";
		default:
			return "";
		}
	
	}
	

}
