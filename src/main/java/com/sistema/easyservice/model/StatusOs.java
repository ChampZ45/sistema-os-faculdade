package com.sistema.easyservice.model;

public enum StatusOs {

		ABERTO("Aberto"),
		ORCAMENTO("Orçamento"),
		EM_ANDAMENTO("Em Andamento"),
		FINALIZADO("Finalizado");

	private String descricao;
	
	StatusOs(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
