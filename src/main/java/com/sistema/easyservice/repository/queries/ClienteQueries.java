package com.sistema.easyservice.repository.queries;

import java.util.List;

import com.sistema.easyservice.repository.filtro.ClienteFiltro;

public interface ClienteQueries {
	
	public List<ClienteFiltro> recuperarClientesPorNomeECpfCnpj(ClienteFiltro filtro);

}
