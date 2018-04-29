package com.sistema.easyservice.repository.queries;

import java.util.List;

import com.sistema.easyservice.dto.GraficoOrdemServicoDTO;
import com.sistema.easyservice.model.OrdemServico;
import com.sistema.easyservice.repository.filtro.OrdemServicoFiltro;

public interface OrdemServicoQueries {

	
	public List<OrdemServico> recuperarOrdemServicoPorCodigoECliente(OrdemServicoFiltro filtro);
	
	public List<GraficoOrdemServicoDTO> recuperarOrdemDeServicoAgrupadoPorMesEAno();
}
