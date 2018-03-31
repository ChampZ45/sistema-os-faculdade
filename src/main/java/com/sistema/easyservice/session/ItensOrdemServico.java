package com.sistema.easyservice.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sistema.easyservice.model.Produto;
import com.sistema.easyservice.model.Servico;

@SessionScope
@Component
public class ItensOrdemServico {

	
	List<Produto> produtos = new ArrayList<>();
	List<Servico> servicos = new ArrayList<>();
	BigDecimal valorTotalProdutos = BigDecimal.ZERO;
	BigDecimal valorTotalServicos = BigDecimal.ZERO;
		
	public void adicionarProduto(Produto produto){
		produtos.add(produto);
	}
	
	public void adicionarServico(Servico servico){
		servicos.add(servico);
	}
	
	public void excluirProduto(Produto produto){
		
		int indice = IntStream.range(0, produtos.size())
				.filter(i -> produtos.get(i).equals(produto))
				.findAny().getAsInt();
		
		produtos.remove(indice);
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public BigDecimal getValorTotalProdutos() {
	valorTotalProdutos = BigDecimal.ZERO;
	produtos.forEach( i ->{ 
					valorTotalProdutos = valorTotalProdutos.add(i.getPreco()); 				
				});
	
	return valorTotalProdutos;
	}
	
	public BigDecimal getValorTotalServicos() {
		valorTotalServicos = BigDecimal.ZERO;
		servicos.forEach( i ->{ 
				valorTotalServicos = valorTotalServicos.add(i.getPreco()); 				
					});
		
		return valorTotalServicos;
		}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	public BigDecimal getValorTotal(){
		BigDecimal valorTotal = BigDecimal.ZERO;
		
		valorTotal = valorTotal.add(this.getValorTotalProdutos()).add(this.getValorTotalServicos());
		
		return valorTotal;
	}
	
	
}
