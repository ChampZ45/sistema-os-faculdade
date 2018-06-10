package com.sistema.easyservice.service;

import java.util.List;

import com.sistema.easyservice.dto.GraficoBarrasDTO;
import com.sistema.easyservice.dto.GraficoOrdemServicoDTO;
import com.sistema.easyservice.dto.GraficoPieDTO;
import com.sistema.easyservice.dto.GraficosOrdemServicoDTO;
import com.sistema.easyservice.model.OrdemServico;

public interface OrdemServicoService {

	public void salvar(OrdemServico ordemServico);

	public GraficoBarrasDTO montarGraficoBarras(List<GraficoOrdemServicoDTO> lista);
	
	public GraficoPieDTO montarGraficoPie();
	
	public GraficosOrdemServicoDTO retornarGraficos();
}
