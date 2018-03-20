package com.sistema.easyservice.model;

public enum TipoPessoa {

	
	FISICA("Fisica","CPF"),JURIDICA("Juridica","CNPJ");

	private String descricao;
	private String documento;	
	
	TipoPessoa(String descricao, String documento) {
		this.descricao = descricao;
		this.documento = documento;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}
	
}
