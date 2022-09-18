package com.algaworks.cursoalga.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.cursoalga.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.cursoalga.domain.model.Entrega;
import com.algaworks.cursoalga.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscarEntregaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long id) {
		return entregaRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
	
}
