package com.sistema.easyservice.repository.queries;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.sistema.easyservice.dto.GraficoOrdemServicoDTO;
import com.sistema.easyservice.model.OrdemServico;
import com.sistema.easyservice.repository.filtro.OrdemServicoFiltro;
import com.sistema.easyservice.util.DataUtil;

public class OrdemServicoQueriesImpl implements OrdemServicoQueries {
	
	
	private static final String SQL_RECUPERAR_INDICES_ORDEM_SERVICO = " select  "
																	+  " (COALESCE(sum(p.preco),0)  +  COALESCE(sum(s.preco),0)) as valor_total "
																	+  " ,sum(p.preco)									"
																	+  " ,sum(s.preco) 									"
																	+  "	,  DATE_FORMAT(data_inicial,'%m-%Y')           "
																	+  "	from ordem_servico os                          "
																	+  "	left join os_produto on id_os = os.id          "
																	+  "	left join os_servicos ose on ose.id_os = os.id "
																	+  "	left join produto p on id_produto = p.id   "
																	+  "	left join servico s on s.id = id_servico   "
																	+  "	GROUP BY DATE_FORMAT(data_inicial,'%m-%Y') ";
																		
	
	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)	public List<OrdemServico> recuperarOrdemServicoPorCodigoECliente(OrdemServicoFiltro filtro) {
		
		if(filtro == null)
			filtro = new OrdemServicoFiltro();
		
		String queryString = " from OrdemServico  c ";
		
		if(filtro.getCodigo() != null) {
			queryString +=" where c.id = :id ";
		}
		
		if(filtro.getCliente() != null && filtro.getCliente().getId() != null && filtro.getCodigo() == null) {
			queryString +=" where c.cliente = :idCliente ";
		}else if(filtro.getCliente() != null && filtro.getCliente().getId() != null) {
			queryString +=" and c.cliente = :idCliente";
		}
		
		Query query = manager.createQuery(queryString);

		if(filtro.getCodigo() != null) {
			query.setParameter("id", filtro.getCodigo() );
		}
		
		if(filtro.getCliente() != null && filtro.getCliente().getId() != null) {
			query.setParameter("idCliente", filtro.getCliente());
		}
		
		return query.getResultList();
	}

	@Override
	public List<GraficoOrdemServicoDTO> recuperarOrdemDeServicoAgrupadoPorMesEAno() {
			
		Query query = manager.createNativeQuery(SQL_RECUPERAR_INDICES_ORDEM_SERVICO);
		
		List<Object[]> lista = query.getResultList();
		
		List<GraficoOrdemServicoDTO> listaDTO = new ArrayList<>();
		for(Object[] result : lista){
			
			Date data = null;
			
			try {
				data = DataUtil.dataToString( result[3].toString(), "MM-yyyy");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			listaDTO.add(new GraficoOrdemServicoDTO(data,(BigDecimal) result[0],(BigDecimal) result[1],(BigDecimal) result[2]));
			
		}
				
		return listaDTO;
	}
	
	

}
