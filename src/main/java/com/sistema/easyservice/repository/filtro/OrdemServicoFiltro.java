package com.sistema.easyservice.repository.filtro;

import com.sistema.easyservice.model.Cliente;

public class OrdemServicoFiltro {

	
	private Long codigo;
	private Cliente cliente;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
		
}
