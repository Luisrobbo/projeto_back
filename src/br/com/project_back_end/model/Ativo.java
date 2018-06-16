package br.com.project_back_end.model;

public enum Ativo {
	SIM(1), NAO(0);

	private int codigo;

	Ativo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public static Ativo obterPorCodigo(int codigo) {
		for (Ativo ativo : values()) {
			if (ativo.getCodigo() == codigo) {
				return ativo;
			}
		}
		return null;
	}
}
