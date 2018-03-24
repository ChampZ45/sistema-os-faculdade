package com.sistema.easyservice.model;

public enum FormaDeCalculo {

	HORAS("Horas"),QUANTIDADE("Quantidade");
	
	private String descricao;

	FormaDeCalculo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
