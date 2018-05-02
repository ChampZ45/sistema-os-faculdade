package com.sistema.easyservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ordem_servico")
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Cliente   cliente = null;
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private LocalDate garantia;
	private StatusOs  status;
	private String    descricaoProdutoServico;
	private String 	  defeito;
	private String	  observacao;
	private String    laudoTecnico;
	private Usuario   responsavel = null;
	List<Produto>     produtos = new ArrayList<>();
	List<Servico>     servicos = new ArrayList<>();
	@Transient		BigDecimal valorTotal = BigDecimal.ZERO;
	@Transient      Map<Long, Integer> mapQuantidadesProdutos = new HashMap<>();
	

	public OrdemServico() {
		// TODO Auto-generated constructor stub
	}
		
	public OrdemServico(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull(message="Cliente é obrigatorio")
	@ManyToOne
	@JoinColumn(name= "id_cliente")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
			
	@NotNull(message="Reponsavel é obrigatorio")
	@ManyToOne
	@JoinColumn(name= "id_responsavel")
	public Usuario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	
	//	@Temporal(TemporalType.DATE)
	@NotNull(message="Data incial é obrigatorio")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name ="data_inicial")
	public LocalDate getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}
	
//	@Temporal(TemporalType.DATE)
	@NotNull(message="Data Final é obrigatorio")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name ="data_final")
	public LocalDate getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name ="status")
	public StatusOs getStatus() {
		return status;
	}
	public void setStatus(StatusOs status) {
		this.status = status;
	}
	
	@Column(name ="desc_produto_servico")
	public String getDescricaoProdutoServico() {
		return descricaoProdutoServico;
	}
	public void setDescricaoProdutoServico(String descricaoProdutoServico) {
		this.descricaoProdutoServico = descricaoProdutoServico;
	}
	
	
	@Column(name ="defeito",columnDefinition = "TEXT")
	public String getDefeito() {
		return defeito;
	}
	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}
	
	@Column(name ="observacao",columnDefinition = "TEXT")
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@Column(name ="laudo_tecnico",columnDefinition = "TEXT")
	public String getLaudoTecnico() {
		return laudoTecnico;
	}
	public void setLaudoTecnico(String laudoTecnico) {
		this.laudoTecnico = laudoTecnico;
	}
	
	@ManyToMany
	@JoinTable(name="os_produto",joinColumns = @JoinColumn(name ="id_os"),inverseJoinColumns = @JoinColumn(name = "id_produto"))
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
//	@Size(min = 1, message = "Escolha pelo menos um servico")
	@ManyToMany
	@JoinTable(name="os_servicos",joinColumns = @JoinColumn(name ="id_os"),inverseJoinColumns = @JoinColumn(name = "id_servico"))
	public List<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name ="garantia")
	public LocalDate getGarantia() {
		return garantia;
	}
	public void setGarantia(LocalDate garantia) {
		this.garantia = garantia;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Transient
	public boolean isNovo(){
		return this.getId() == null;
	}
	
	@Transient
	public boolean isFinalizado(){
		return this.status == null ? false : this.status.equals(StatusOs.FINALIZADO);
	}
	
	@Transient
	public void adicionarProduto(Produto produto){
		this.produtos.add(produto);
	}

	@Transient
	public Map<Long, Integer> getMapQuantidadesProdutos() {
		return mapQuantidadesProdutos;
	}

	@Transient
	public void setMapQuantidadesProdutos(Map<Long, Integer> mapQuantidadesProdutos) {
		this.mapQuantidadesProdutos = mapQuantidadesProdutos;
	}

	@Transient
	public BigDecimal getValorTotal(){
				
		produtos.forEach( i ->{ 
			valorTotal = valorTotal.add(i.getPreco()); 				
					});
		
		servicos.forEach( i ->{ 
			valorTotal = valorTotal.add(i.getPreco()); 				
				});
		
		return valorTotal;
	}
	
}
