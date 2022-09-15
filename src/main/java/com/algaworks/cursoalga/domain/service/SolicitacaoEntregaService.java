package com.algaworks.cursoalga.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.cursoalga.domain.exception.NegocioException;
import com.algaworks.cursoalga.domain.model.Cliente;
import com.algaworks.cursoalga.domain.model.Entrega;
import com.algaworks.cursoalga.domain.model.StatusEntrega;
import com.algaworks.cursoalga.domain.repository.ClienteRepository;
import com.algaworks.cursoalga.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private EntregaRepository entregaRepository;
	private ClienteService clienteService;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		return entregaRepository.save(entrega);
	}
	
}
