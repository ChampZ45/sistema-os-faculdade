package com.sistema.easyservice.repository.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.sistema.easyservice.model.OrdemServico;
import com.sistema.easyservice.repository.filtro.OrdemServicoFiltro;

public class OrdemServicoQueriesImpl implements OrdemServicoQueries {
	
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

}
