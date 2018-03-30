package com.sistema.easyservice.repository.queries;

import java.util.List;

import com.sistema.easyservice.model.Servico;
import com.sistema.easyservice.repository.filtro.ServicoFiltro;

public interface ServicoQueries {
	
	public List<Servico> recuperarPorCodigoNomeEDescricao(ServicoFiltro filtro);

}
