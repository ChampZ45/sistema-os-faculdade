package com.sistema.easyservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GraficoOrdemServicoDTO {

	private Date dataInicial;
	private BigDecimal valorServico = BigDecimal.ZERO;
	private BigDecimal valorProduto = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	
	public GraficoOrdemServicoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public GraficoOrdemServicoDTO(Date dataInicial , BigDecimal valorTotal,BigDecimal valorProduto,BigDecimal valorServico) {
		this.dataInicial = dataInicial;
		this.valorTotal = valorTotal;
		this.valorServico = valorServico;
		this.valorProduto = valorProduto;
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public BigDecimal getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
	}
		
	
	
	
}
