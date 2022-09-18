package com.algaworks.cursoalga.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.cursoalga.domain.model.Entrega;
import com.algaworks.cursoalga.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private EntregaRepository entregaRepository;
	private BuscarEntregaService buscarEntregaService;
	
	@Transactional
	public void finalizar(Long id) {
		Entrega entrega = buscarEntregaService.buscar(id);
		entrega.finalizar();
		entregaRepository.save(entrega);
	}
	
}
