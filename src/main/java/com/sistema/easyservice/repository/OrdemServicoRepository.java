package com.sistema.easyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.easyservice.model.OrdemServico;
import com.sistema.easyservice.repository.queries.OrdemServicoQueries;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>, OrdemServicoQueries {

}
