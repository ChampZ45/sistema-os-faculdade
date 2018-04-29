package com.sistema.easyservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sistema.easyservice.dto.GraficoBarrasDTO;
import com.sistema.easyservice.dto.GraficoOrdemServicoDTO;
import com.sistema.easyservice.repository.OrdemServicoRepository;
import com.sistema.easyservice.service.OrdemServicoService;

@Controller
public class HomeController {
	
	@Autowired OrdemServicoService ordemServicoService;
	@Autowired OrdemServicoRepository ordemServicoRepository;
	
	
	@RequestMapping(value = "/home/graficoBarras", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> carregarGraficoHome(){
				
		List<GraficoOrdemServicoDTO> lista = ordemServicoRepository.recuperarOrdemDeServicoAgrupadoPorMesEAno();
		
		GraficoBarrasDTO dto = ordemServicoService.montarGraficoBarras(lista);
				
		return new ResponseEntity<GraficoBarrasDTO>(dto,HttpStatus.OK);
		
	}
}
