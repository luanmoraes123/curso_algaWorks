package com.algaworks.cursoalga.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.cursoalga.domain.model.Entrega;
import com.algaworks.cursoalga.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscarEntregaService buscaEntrega;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = buscaEntrega.buscar(entregaId);
		return entrega.adicionarOcorrencia(descricao);
	}
}
