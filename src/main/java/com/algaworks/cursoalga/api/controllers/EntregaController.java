package com.algaworks.cursoalga.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.cursoalga.api.assembler.EntregaAssembler;
import com.algaworks.cursoalga.api.model.EntregaModel;
import com.algaworks.cursoalga.api.model.input.EntregaInput;
import com.algaworks.cursoalga.domain.model.Entrega;
import com.algaworks.cursoalga.domain.repository.EntregaRepository;
import com.algaworks.cursoalga.domain.service.FinalizacaoEntregaService;
import com.algaworks.cursoalga.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	private FinalizacaoEntregaService finalizacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega entrega = entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(entrega);
		return entregaAssembler.toModel(entregaSolicitada);
	}
	
	@PutMapping("/{id}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long id) {
		finalizacaoEntregaService.finalizar(id);
	}
	
	@GetMapping
	public List<EntregaModel> listar(){
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long id){
		return entregaRepository.findById(id).map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega))
		).orElse(ResponseEntity.notFound().build());
		}
	
}
