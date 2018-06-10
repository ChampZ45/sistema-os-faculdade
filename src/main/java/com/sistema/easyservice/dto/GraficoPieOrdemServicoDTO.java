package com.sistema.easyservice.dto;

import java.math.BigInteger;
import java.util.Date;

public class GraficoPieOrdemServicoDTO {
	
	private Date dataIncial;
	private BigInteger quantidade;
	
	public GraficoPieOrdemServicoDTO(Date dataInicial , BigInteger quantidade) {
		this.dataIncial = dataInicial;
		this.quantidade = quantidade;
	}



	public Date getDataIncial() {
		return dataIncial;
	}



	public void setDataIncial(Date dataIncial) {
		this.dataIncial = dataIncial;
	}



	public BigInteger getQuantidade() {
		return quantidade;
	}



	public void setQuantidade(BigInteger quantidade) {
		this.quantidade = quantidade;
	}
	
	
	

}
