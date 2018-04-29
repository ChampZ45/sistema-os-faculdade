package com.sistema.easyservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.easyservice.dto.DataSets;
import com.sistema.easyservice.dto.GraficoBarrasDTO;
import com.sistema.easyservice.dto.GraficoOrdemServicoDTO;
import com.sistema.easyservice.model.OrdemServico;
import com.sistema.easyservice.repository.OrdemServicoRepository;
import com.sistema.easyservice.util.DataUtil;

@Service
public class OrdemServicoImpl implements OrdemServicoService {

	
	private static final int VALOR_PRODUTO = 0;
	private static final int VALOR_SERVICO = 1;
	private static final int VALOR_TOTAL = 2;
	
	@Autowired private OrdemServicoRepository ordemServicoRepository;
	
	@Override
	@Transactional
	public void salvar(OrdemServico ordemServico) {

		ordemServicoRepository.save(ordemServico);
	}

	@Override
	public GraficoBarrasDTO montarGraficoBarras(List<GraficoOrdemServicoDTO> lista) {
		
		if (lista.isEmpty())
			return new GraficoBarrasDTO();

		GraficoBarrasDTO graficoDTO = new GraficoBarrasDTO();
		
		for (GraficoOrdemServicoDTO dto : lista) {
			
			String dataFormatada = DataUtil.dataFormatada("MM/yyyy", dto.getDataInicial());

			graficoDTO.getLabels().add(dataFormatada);
			DataSets dataset = null;
			
		   if(graficoDTO.getDatasets().isEmpty()){
				dataset  = new DataSets();
			    dataset.setLabel("Produtos");
			    
			    dataset.setBackgroundColor("rgba(255, 99, 132, 0.2)");			    
			    dataset.setBorderColor("rgb(255, 99, 132)");
			    
			    dataset.getData().add(dto.getValorProduto());
			    graficoDTO.getDatasets().add(VALOR_PRODUTO,dataset);
			    
			    dataset  = new DataSets();
			    dataset.setLabel("Serviços");
			    
			    dataset.setBackgroundColor("rgba(75, 192, 192, 0.2)");			    
			    dataset.setBorderColor("rgb(75, 192, 192)");
			    
			    dataset.getData().add(dto.getValorServico());
			    graficoDTO.getDatasets().add(VALOR_SERVICO,dataset);
			    
			    dataset  = new DataSets();
			    dataset.setLabel("Total");
			    dataset.setBackgroundColor("rgba(54, 162, 235, 0.2)");
			    dataset.setBorderColor("rgb(54, 162, 235)");
			    
			    dataset.getData().add(dto.getValorTotal());
			    graficoDTO.getDatasets().add(VALOR_TOTAL,dataset);
			    
			}else{
				dataset = graficoDTO.getDatasets().get(VALOR_PRODUTO);
				dataset.getData().add(dto.getValorProduto());
				
				dataset = graficoDTO.getDatasets().get(VALOR_SERVICO);
				dataset.getData().add(dto.getValorServico());
				
				dataset = graficoDTO.getDatasets().get(VALOR_TOTAL);
				dataset.getData().add(dto.getValorTotal());
			}
			
					
		}
		
		return graficoDTO;
	}

}
