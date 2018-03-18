package com.sistema.easyservice.service;

import com.sistema.easyservice.model.Cliente;

public interface ClienteService {

	
	public void salvarCliente(Cliente cliente);
	
	boolean validarCliente(Cliente cliente);
}
