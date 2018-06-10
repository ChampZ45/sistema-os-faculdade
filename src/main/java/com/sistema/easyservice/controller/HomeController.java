package com.sistema.easyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sistema.easyservice.dto.GraficosOrdemServicoDTO;
import com.sistema.easyservice.repository.OrdemServicoRepository;
import com.sistema.easyservice.service.OrdemServicoService;

@Controller
public class HomeController {
	
	@Autowired OrdemServicoService ordemServicoService;
	@Autowired OrdemServicoRepository ordemServicoRepository;
	
	
	@RequestMapping(value = "/home/graficoBarras", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> carregarGraficoHome(){
				
		GraficosOrdemServicoDTO graficosDTO = ordemServicoService.retornarGraficos();
				
		return new ResponseEntity<GraficosOrdemServicoDTO>(graficosDTO,HttpStatus.OK);
		
	}
}
