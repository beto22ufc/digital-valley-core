package model;

public enum EnumCargo {
	
	PROFESSOR("Professor"), SECRETARIO("Secretario"), FUNCIONARIO("Funcionario");

	public String cargo;

	EnumCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}
	
	public static EnumCargo getByString(String value){
		switch (value) {
		case "Professor":
			return EnumCargo.PROFESSOR;
			
		case "Secretario":
			return EnumCargo.SECRETARIO;
			
		case "Funcionario":
			return EnumCargo.FUNCIONARIO;
		

		default:
			return null;
			
		}
	}
}