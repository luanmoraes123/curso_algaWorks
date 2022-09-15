package com.algaworks.cursoalga.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.cursoalga.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNome(String Nome);
}
