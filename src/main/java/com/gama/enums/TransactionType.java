package com.gama.enums;

/**
 * Enumerador dos tipos de transações realizadas, sendo: <b>R</b> - Receitas
 * <b>D</b> - Débitos <b>T</b> - Transferências
 *
 */
public enum TransactionType {
	R("RECEITA"), D("DESPESA"), T("TRANSFERENCIA ENTRE CONTAS");//, TU("TRANSFERENCIA ENTRE USUARIOS");

private String description;

private TransactionType(String description) {
	this.description=description;
}

	public String getDescription() {
		return description;
	}
	
	public static String typeToString(TransactionType t)
	{
		switch(t)
		{
		case R:
			return "R";
		case T:
			return "T";
		case D:
			return "D";
		default:
			throw new IllegalArgumentException();
		}
	}

}
