package com.sistema.easyservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.easyservice.model.Cliente;
import com.sistema.easyservice.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public List<Usuario> findByNomeStartingWithIgnoreCase(String nome);

}
